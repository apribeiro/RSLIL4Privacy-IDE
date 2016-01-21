/*
 * generated by Xtext
 */
package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.xtext.example.mydsl.myDsl.Attribute;
import org.xtext.example.mydsl.myDsl.Collection;
import org.xtext.example.mydsl.myDsl.Disclosure;
import org.xtext.example.mydsl.myDsl.Enforcement;
import org.xtext.example.mydsl.myDsl.Import;
import org.xtext.example.mydsl.myDsl.Informative;
import org.xtext.example.mydsl.myDsl.MyDslPackage;
import org.xtext.example.mydsl.myDsl.Partof;
import org.xtext.example.mydsl.myDsl.Policy;
import org.xtext.example.mydsl.myDsl.PrivateData;
import org.xtext.example.mydsl.myDsl.Recipient;
import org.xtext.example.mydsl.myDsl.RefPrivateData;
import org.xtext.example.mydsl.myDsl.ReferToRecipient;
import org.xtext.example.mydsl.myDsl.ReferToRecipientSource;
import org.xtext.example.mydsl.myDsl.ReferToRecipientTarget;
import org.xtext.example.mydsl.myDsl.ReferToService;
import org.xtext.example.mydsl.myDsl.RefertoEnforcement;
import org.xtext.example.mydsl.myDsl.Retention;
import org.xtext.example.mydsl.myDsl.Service;
import org.xtext.example.mydsl.myDsl.ServicePartof;
import org.xtext.example.mydsl.myDsl.Usage;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;

@SuppressWarnings("all")
public class MyDslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private MyDslGrammarAccess grammarAccess;
	
	@Override
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == MyDslPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case MyDslPackage.ATTRIBUTE:
				sequence_Attribute(context, (Attribute) semanticObject); 
				return; 
			case MyDslPackage.COLLECTION:
				sequence_Collection(context, (Collection) semanticObject); 
				return; 
			case MyDslPackage.DISCLOSURE:
				sequence_Disclosure(context, (Disclosure) semanticObject); 
				return; 
			case MyDslPackage.ENFORCEMENT:
				sequence_Enforcement(context, (Enforcement) semanticObject); 
				return; 
			case MyDslPackage.IMPORT:
				sequence_Import(context, (Import) semanticObject); 
				return; 
			case MyDslPackage.INFORMATIVE:
				sequence_Informative(context, (Informative) semanticObject); 
				return; 
			case MyDslPackage.PARTOF:
				sequence_Partof(context, (Partof) semanticObject); 
				return; 
			case MyDslPackage.POLICY:
				sequence_Policy(context, (Policy) semanticObject); 
				return; 
			case MyDslPackage.PRIVATE_DATA:
				sequence_PrivateData(context, (PrivateData) semanticObject); 
				return; 
			case MyDslPackage.RECIPIENT:
				sequence_Recipient(context, (Recipient) semanticObject); 
				return; 
			case MyDslPackage.REF_PRIVATE_DATA:
				sequence_RefPrivateData(context, (RefPrivateData) semanticObject); 
				return; 
			case MyDslPackage.REFER_TO_RECIPIENT:
				sequence_ReferToRecipient(context, (ReferToRecipient) semanticObject); 
				return; 
			case MyDslPackage.REFER_TO_RECIPIENT_SOURCE:
				sequence_ReferToRecipientSource(context, (ReferToRecipientSource) semanticObject); 
				return; 
			case MyDslPackage.REFER_TO_RECIPIENT_TARGET:
				sequence_ReferToRecipientTarget(context, (ReferToRecipientTarget) semanticObject); 
				return; 
			case MyDslPackage.REFER_TO_SERVICE:
				sequence_ReferToService(context, (ReferToService) semanticObject); 
				return; 
			case MyDslPackage.REFERTO_ENFORCEMENT:
				sequence_RefertoEnforcement(context, (RefertoEnforcement) semanticObject); 
				return; 
			case MyDslPackage.RETENTION:
				sequence_Retention(context, (Retention) semanticObject); 
				return; 
			case MyDslPackage.SERVICE:
				sequence_Service(context, (Service) semanticObject); 
				return; 
			case MyDslPackage.SERVICE_PARTOF:
				sequence_ServicePartof(context, (ServicePartof) semanticObject); 
				return; 
			case MyDslPackage.USAGE:
				sequence_Usage(context, (Usage) semanticObject); 
				return; 
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (name=STRING attributeName=STRING)
	 */
	protected void sequence_Attribute(EObject context, Attribute semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.ATTRIBUTE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.ATTRIBUTE__NAME));
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.ATTRIBUTE__ATTRIBUTE_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.ATTRIBUTE__ATTRIBUTE_NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAttributeAccess().getNameSTRINGTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAttributeAccess().getAttributeNameSTRINGTerminalRuleCall_3_0(), semanticObject.getAttributeName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=STRING 
	 *         condition=STRING 
	 *         partof=[Collection|ID]? 
	 *         refprivatedata+=RefPrivateData* 
	 *         refertoservice+=ReferToService* 
	 *         refertoEnforcement+=RefertoEnforcement* 
	 *         (modalitykind='Permitted' | modalitykind='Obligation' | modalitykind='Forbidden')
	 *     )
	 */
	protected void sequence_Collection(EObject context, Collection semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=STRING 
	 *         condition=STRING 
	 *         partof=[Disclosure|ID]? 
	 *         referToRecipient+=ReferToRecipient* 
	 *         referToRecipientsource+=ReferToRecipientSource* 
	 *         referToRecipienttarget+=ReferToRecipientTarget* 
	 *         refprivatedata+=RefPrivateData* 
	 *         refertoservice+=ReferToService* 
	 *         refertoEnforcement+=RefertoEnforcement* 
	 *         (modalitykind='Permitted' | modalitykind='Obligation' | modalitykind='Forbidden')
	 *     )
	 */
	protected void sequence_Disclosure(EObject context, Disclosure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         enforcementName=STRING 
	 *         enforcementDescription=STRING 
	 *         (enforcementKind='Action' | enforcementKind='Algorithm' | enforcementKind='Config' | enforcementKind='Process' | enforcementKind='Tool')
	 *     )
	 */
	protected void sequence_Enforcement(EObject context, Enforcement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildcard
	 */
	protected void sequence_Import(EObject context, Import semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=STRING 
	 *         condition=STRING 
	 *         partof=[Informative|ID]? 
	 *         refprivatedata+=RefPrivateData* 
	 *         refertoservice+=ReferToService* 
	 *         refertoEnforcement+=RefertoEnforcement* 
	 *         (modalitykind='Permitted' | modalitykind='Obligation' | modalitykind='Forbidden')
	 *     )
	 */
	protected void sequence_Informative(EObject context, Informative semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     partof=[Recipient|ID]
	 */
	protected void sequence_Partof(EObject context, Partof semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.PARTOF__PARTOF) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.PARTOF__PARTOF));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPartofAccess().getPartofRecipientIDTerminalRuleCall_0_0_1(), semanticObject.getPartof());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=QualifiedName 
	 *         importelements+=Import* 
	 *         collection+=Collection* 
	 *         disclosure+=Disclosure* 
	 *         retention+=Retention* 
	 *         usage+=Usage* 
	 *         informative+=Informative* 
	 *         privateData+=PrivateData* 
	 *         recipient+=Recipient* 
	 *         service+=Service* 
	 *         enforcement+=Enforcement*
	 *     )
	 */
	protected void sequence_Policy(EObject context, Policy semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID privatedata=STRING (PrivateDataKind='PersonalInformation' | PrivateDataKind='UsageInformation') attribute+=Attribute*)
	 */
	protected void sequence_PrivateData(EObject context, PrivateData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         recipientname=STRING 
	 *         description=STRING 
	 *         partof+=Partof* 
	 *         (RecipientScopeKind='Internal' | RecipientScopeKind='External' | RecipientScopeKind='Internal/External') 
	 *         (RecipientTypeKind='Individual' | RecipientTypeKind='Organization' | RecipientTypeKind='Individual/Organization')
	 *     )
	 */
	protected void sequence_Recipient(EObject context, Recipient semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     refpr=[PrivateData|ID]
	 */
	protected void sequence_RefPrivateData(EObject context, RefPrivateData semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REF_PRIVATE_DATA__REFPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REF_PRIVATE_DATA__REFPR));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRefPrivateDataAccess().getRefprPrivateDataIDTerminalRuleCall_0_0_1(), semanticObject.getRefpr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     refertore=[Recipient|ID]
	 */
	protected void sequence_ReferToRecipientSource(EObject context, ReferToRecipientSource semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT_SOURCE__REFERTORE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT_SOURCE__REFERTORE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getReferToRecipientSourceAccess().getRefertoreRecipientIDTerminalRuleCall_0_0_1(), semanticObject.getRefertore());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     refertore=[Recipient|ID]
	 */
	protected void sequence_ReferToRecipientTarget(EObject context, ReferToRecipientTarget semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT_TARGET__REFERTORE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT_TARGET__REFERTORE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getReferToRecipientTargetAccess().getRefertoreRecipientIDTerminalRuleCall_0_0_1(), semanticObject.getRefertore());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     refertore=[Recipient|ID]
	 */
	protected void sequence_ReferToRecipient(EObject context, ReferToRecipient semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT__REFERTORE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REFER_TO_RECIPIENT__REFERTORE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getReferToRecipientAccess().getRefertoreRecipientIDTerminalRuleCall_0_0_1(), semanticObject.getRefertore());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     refertose=[Service|ID]
	 */
	protected void sequence_ReferToService(EObject context, ReferToService semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REFER_TO_SERVICE__REFERTOSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REFER_TO_SERVICE__REFERTOSE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getReferToServiceAccess().getRefertoseServiceIDTerminalRuleCall_0_0_1(), semanticObject.getRefertose());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     refst=[Enforcement|ID]
	 */
	protected void sequence_RefertoEnforcement(EObject context, RefertoEnforcement semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.REFERTO_ENFORCEMENT__REFST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.REFERTO_ENFORCEMENT__REFST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRefertoEnforcementAccess().getRefstEnforcementIDTerminalRuleCall_0_0_1(), semanticObject.getRefst());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=STRING 
	 *         condition=STRING 
	 *         partof=[Retention|ID]? 
	 *         period=STRING 
	 *         refprivatedata+=RefPrivateData* 
	 *         refertoservice+=ReferToService* 
	 *         refertoEnforcement+=RefertoEnforcement* 
	 *         (modalitykind='Permitted' | modalitykind='Obligation' | modalitykind='Forbidden')
	 *     )
	 */
	protected void sequence_Retention(EObject context, Retention semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     refertoservice=[Service|ID]
	 */
	protected void sequence_ServicePartof(EObject context, ServicePartof semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MyDslPackage.Literals.SERVICE_PARTOF__REFERTOSERVICE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MyDslPackage.Literals.SERVICE_PARTOF__REFERTOSERVICE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getServicePartofAccess().getRefertoserviceServiceIDTerminalRuleCall_0_0_1(), semanticObject.getRefertoservice());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID servicename=STRING description=STRING? refprivatedata+=RefPrivateData* servicepartof+=ServicePartof*)
	 */
	protected void sequence_Service(EObject context, Service semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         description=STRING 
	 *         condition=STRING 
	 *         partof=[Usage|ID]? 
	 *         refprivatedata+=RefPrivateData* 
	 *         refertoservice+=ReferToService* 
	 *         refertoEnforcement+=RefertoEnforcement* 
	 *         (modalitykind='Permitted' | modalitykind='Obligation' | modalitykind='Forbidden')
	 *     )
	 */
	protected void sequence_Usage(EObject context, Usage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
