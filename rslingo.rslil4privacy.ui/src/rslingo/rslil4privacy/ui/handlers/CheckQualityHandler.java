package rslingo.rslil4privacy.ui.handlers;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.util.StringInputStream;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import eddy.lang.Policy;
import eddy.lang.analysis.Conflict;
import eddy.lang.analysis.ConflictAnalyzer;
import eddy.lang.analysis.ConflictPrinter;
import eddy.lang.analysis.Extension;
import eddy.lang.analysis.ExtensionCalculator;
import eddy.lang.parser.Compilation;
import eddy.lang.parser.Compiler;
import eddy.lang.parser.Parser;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLClass;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import rslingo.rslil4privacy.ui.windows.MenuCommand;
import rslingo.rslil4privacy.ui.windows.MenuCommandWindow;
import uk.ac.man.cs.mig.coode.owlviz.model.OWLKBAssertedGraphModel;
import uk.ac.man.cs.mig.coode.owlviz.ui.renderer.OWLClsNodeLabelRenderer;
import uk.ac.man.cs.mig.coode.owlviz.ui.renderer.OWLClsNodeRenderer;
import uk.ac.man.cs.mig.util.graph.controller.impl.DefaultController;
import uk.ac.man.cs.mig.util.graph.export.impl.PNGExportFormat;
import uk.ac.man.cs.mig.util.graph.layout.dotlayoutengine.DotLayoutEngineProperties;

public class CheckQualityHandler extends AbstractHandler {

	private static final String GEN_FOLDER = "src-gen";
	private static final String FILE_EXT = ".policy";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		
		// Check if the command was triggered using the ContextMenu
		if (selection != null) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			IFile file = (IFile) structuredSelection.getFirstElement();
			IProject project = file.getProject();
			callEddyReasoner(project, file);
		} else {
			IWorkbenchWindow workbenchWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			MenuCommand cmd = new MenuCommand() {
				@Override
				public void execute(IProject project, IFile file) {
					callEddyReasoner(project, file);
				}
			};
			MenuCommandWindow window = new MenuCommandWindow(workbenchWindow.getShell(),
					cmd, false, FILE_EXT);
			window.open();
		}
		
		return null;
	}

	private void callEddyReasoner(IProject project, IFile file) {
		try {
			IFolder srcGenFolder = project.getFolder(GEN_FOLDER);
            
	        if (!srcGenFolder.exists()) {
	            try {
					srcGenFolder.create(true, true, new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
	        }
			
			String pluginPath = Platform.getInstallLocation()
					.getURL().getPath().substring(1)
					+ "plugins/RSLingo4Privacy/";
			String policyBase = "policy-base.owl";
			String fileName = file.getName().split(FILE_EXT)[0];
			StringBuilder logger = new StringBuilder();
			boolean useLocal = true;
			
			long time = System.currentTimeMillis();
			Parser parser = new Parser();
			Policy policy = parser.parse(new File(file.getLocation().toOSString()));
			Compiler compiler = new Compiler();
			
			// Use the local copy of the upper ontology
			if (useLocal) {
				IRI docIRI = IRI.create("http://gaius.isri.cmu.edu/2011/8/policy-base.owl");
				SimpleIRIMapper mapper = new SimpleIRIMapper(docIRI, IRI.create(
						new File(pluginPath + policyBase)));
				compiler.getManager().addIRIMapper(mapper);
				
				// Tell extension calculator to use local ontology
				ExtensionCalculator.setOntologyBasePolicy(pluginPath + policyBase);
			}
			
			// Compile the policy
			Compilation comp = compiler.compile(policy);
			time = System.currentTimeMillis() - time;
			addToLog(logger, file.getName() + ": Parsing policy... " + (time / 1000.0) + " secs");
			
			// Compute extension and detect conflicts
			time = System.currentTimeMillis();
			
			ConflictAnalyzer analyzer = new ConflictAnalyzer();
			ExtensionCalculator calc = new ExtensionCalculator();
			Extension ext = calc.extend(comp);
			ArrayList<Conflict> conflicts = analyzer.analyze(ext);
			
			time = System.currentTimeMillis() - time;
			addToLog(logger, file.getName() + ": Detecting conflicts... " + (time / 1000.0) + " secs\n");
			
			// Report the conflicts
			ByteArrayOutputStream osConflicts = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(osConflicts);
//			ConflictPrinter printer = new ConflictPrinter(System.err);
			ConflictPrinter printer = new ConflictPrinter(ps);
			TreeSet<String> rules = new TreeSet<String>();
			
			for (Conflict c : conflicts) {
				rules.add(c.rule1.id);
				rules.add(c.rule2.id);
				printer.print(c);
				printer.println();
			}
			addToLog(logger, osConflicts.toString());
			osConflicts.close();
			printer.close();
			
			if (conflicts.size() > 0) {
				addToLog(logger, file.getName() + ": Found " + conflicts.size() + " conflicting interpretations across " + rules.size() + " rules");
			}
			else {
				addToLog(logger, file.getName() + ": No conflicts found");
			}
			
			// Save the ontology to a file for inspection
			String projectPath = file.getProject().getLocation().toOSString();
			String genPath = projectPath + "/" + GEN_FOLDER + "/";
			OWLOntology ontology = comp.getOntology();
			OWLOntologyManager manager = ontology.getOWLOntologyManager();
			manager.saveOntology(ontology, IRI.create(
					new File(genPath + fileName + ".owl")));
			addToLog(logger, file.getName() + ": Saved ontology as '" + fileName + ".owl'");
			
			addToLog(logger, file.getName() + ": Finished.\n");
			
			// Performance Indicators
//			CompilationProfile.computeProfile(comp);
//			comp.printProperties(System.out);
//			ByteArrayOutputStream osProperties = new ByteArrayOutputStream();
//			PrintStream ps2 = new PrintStream(osProperties);
//			comp.printProperties(ps2);
//			addToLog(logger, osProperties.toString());
//			osProperties.close();
//			ps2.close();

			generateOwlPng(genPath, fileName);
			
			IFile logFile = srcGenFolder.getFile(fileName + ".log");
			InputStream source = new StringInputStream(logger.toString());
			
			if (!logFile.exists()) {
				logFile.create(source, IResource.FORCE, new NullProgressMonitor());
			} else {
				logFile.setContents(source, IResource.FORCE, new NullProgressMonitor());
			}
			
			// Refresh the project
			file.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateOwlPng(String genPath, String fileName) throws Exception {
		FileInputStream is = new FileInputStream(genPath + fileName + ".owl"); 
		OWLModel model = ProtegeOWL.createJenaOWLModelFromInputStream(is);
		
		final DefaultController controller = new DefaultController(
				new OWLKBAssertedGraphModel(model));
		controller.setNodeRenderer(
				new OWLClsNodeRenderer(controller, controller.getVisualisedObjectManager(),
						new OWLClsNodeLabelRenderer(), model));		
		
		OWLNamedClass thing = model.getOWLNamedClass("owl:Thing");
		
		if (thing != null) {
			controller.getVisualisedObjectManager().showObject(thing);
			controller.getVisualisedObjectManager().showChildren(thing, 4, OWLClass.class);
		}
		
		controller.getGraphView().setPreferredSize(new Dimension(400, 400));

		DotLayoutEngineProperties properties = DotLayoutEngineProperties.getInstance();
		properties.setDotProcessPath("C:/Program Files (x86)/Graphviz2.24/bin/dot.exe");
		controller.getGraphView().revalidateGraph();

		String path = genPath + fileName + ".png";
		PNGExportFormat exportFormat = new PNGExportFormat();
		
		FileOutputStream fos = new FileOutputStream(path);
		exportFormat.export(controller, fos);
		fos.close();
	}
	
	private void addToLog(StringBuilder logger, String message) {
		System.err.println(message);
		logger.append(message).append("\n");
	}
}
