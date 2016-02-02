package org.xtext.example.mydsl.ui.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xtext.example.mydsl.MyDslStandaloneSetup;
import org.xtext.example.mydsl.myDsl.Attribute;
import org.xtext.example.mydsl.myDsl.PrivateData;
import org.xtext.example.mydsl.myDsl.impl.PolicyImpl;
import org.xtext.example.mydsl.ui.windows.MenuCommand;
import org.xtext.example.mydsl.ui.windows.MenuCommandWindow;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

public class WordHandler extends AbstractHandler {

	private static final String GEN_FOLDER = "src-gen";
	private static final String DOCS_FOLDER = "docs";
	private static final String FILE_EXT = ".mydsl";
	private static final String DEF_WORD_PATH = "RSL-IL4Privacy-WordTemplate.docx";
	
	private final String PLUGIN_PATH = Platform.getInstallLocation()
			.getURL().getPath().substring(1)
			+ "plugins/RSLingo4Privacy/";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		
		// Check if the command was triggered using the ContextMenu
		if (selection != null) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			IFile file = (IFile) structuredSelection.getFirstElement();
			generateWordFile(file);
		} else {
			IWorkbenchWindow workbenchWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			MenuCommand cmd = new MenuCommand() {
				@Override
				public void execute(IProject project, IFile file) {
					generateWordFile(file);
				}
			};
			MenuCommandWindow window = new MenuCommandWindow(workbenchWindow.getShell(),
					cmd, false, FILE_EXT);
			window.open();
		}
		
		return null;
	}

	private void generateWordFile(IFile file) {
		IProject project = file.getProject();
		IFolder srcGenFolder = project.getFolder(GEN_FOLDER);
        
        try {
        	if (!srcGenFolder.exists()) {
                srcGenFolder.create(true, true, new NullProgressMonitor());
            }
    		
            IFolder docsFolder = srcGenFolder.getFolder(DOCS_FOLDER);
    		
    		if (!docsFolder.exists()) {
    			docsFolder.create(true, true, new NullProgressMonitor());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Start a new Thread to avoid blocking the UI
        Runnable runnable = new Runnable() {
			@Override
			public void run() {
				new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
	            Injector injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	            XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
	            resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	            Resource resource = resourceSet.getResource(
	                URI.createURI("platform:/resource/" + file.getFullPath().toString()), true);
	            	//URI.createURI("platform:/resource/org.xtext.example.mydsl/src/example.mydsl"), true);
	            PolicyImpl policy = (PolicyImpl) resource.getContents().get(0);

	            // TODO Generate Word file
	            try {
	            	InputStream from = new FileInputStream(PLUGIN_PATH + DEF_WORD_PATH);
	            	XWPFDocument document = new XWPFDocument(from);
		            
		            for (PrivateData data : Lists.reverse(policy.getPrivateData())) {
		            	XWPFParagraph tEnd = DocumentHelper.getParagraph(document, "@PDEnd");
		            	// Get the position of the paragraph after the end tag
		            	int endPos = document.getParagraphPos(document.getPosOfParagraph(tEnd)) + 1;
		            	tEnd = document.getParagraphArray(endPos);
			            XmlCursor cursor = tEnd.getCTP().newCursor();
			            
			            XWPFParagraph tName = DocumentHelper.getParagraph(document, "@PDName");
			            XWPFParagraph nName = document.insertNewParagraph(cursor);
			            DocumentHelper.cloneParagraph(nName, tName);
			            DocumentHelper.replaceText(nName, "@PDName", data.getPrivatedata()
			            		+ " (" + data.getName() + ")");
			            
			            XWPFParagraph tType = DocumentHelper.getParagraph(document, "@PDType");
			            cursor = tEnd.getCTP().newCursor();
			            XWPFParagraph nType = document.insertNewParagraph(cursor);
			            DocumentHelper.cloneParagraph(nType, tType);
			            DocumentHelper.replaceText(nType, "@PDType", data.getPrivateDataKind());
			            
			            XWPFParagraph tDesc = DocumentHelper.getParagraph(document, "@PDDescription");
			            cursor = tEnd.getCTP().newCursor();
			            XWPFParagraph nDesc = document.insertNewParagraph(cursor);
			            DocumentHelper.cloneParagraph(nDesc, tDesc);
			            DocumentHelper.replaceText(nDesc, "@PDDescription", data.getPrivatedata());
			            
			            XWPFParagraph nAttr = null;
			            
			            for (Attribute attr : data.getAttribute()) {
			            	XWPFParagraph tAttr = DocumentHelper.getParagraph(document, "@PDAttrName");
			            	cursor = tEnd.getCTP().newCursor();
				            nAttr = document.insertNewParagraph(cursor);
				            DocumentHelper.cloneParagraph(nAttr, tAttr);
				            DocumentHelper.replaceText(nAttr, "@PDAttrName", attr.getName());
				            DocumentHelper.replaceText(nAttr, "@PDAttrDescription", attr.getAttributeName());
			            }
			            
			            // Add a newline to the last paragraph
			            if (nAttr != null) {
							DocumentHelper.addLineBreakToParagraph(nAttr);
						}
					}
		            
		            // Delete Private Data Tags paragraphs
		            XWPFParagraph tName = DocumentHelper.getParagraph(document, "@PDStart");
		            int posStart = document.getParagraphPos(document.getPosOfParagraph(tName));
		            XWPFParagraph tEnd = DocumentHelper.getParagraph(document, "@PDEnd");
		            int posEnd = document.getParagraphPos(document.getPosOfParagraph(tEnd));
		            List<XWPFParagraph> tParagraphs = new ArrayList<XWPFParagraph>(document.getParagraphs().subList(posStart, posEnd + 1));
		            
		            for (XWPFParagraph tp : tParagraphs) {
		            	document.removeBodyElement(document.getPosOfParagraph(tp));
					}
		            
		            // Write the Document in file system
		            File to = new File(project.getLocation().toOSString()
            				+ "/" + GEN_FOLDER + "/" + DOCS_FOLDER 
            				+ "/" + file.getName() + ".docx");
		            FileOutputStream out = new FileOutputStream(to);
		            document.write(out);
		            out.close();
		            document.close();
		            
		            project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		            
		            System.out.println(file.getName() + ".docx written successfully");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(runnable).start();
	}
}
