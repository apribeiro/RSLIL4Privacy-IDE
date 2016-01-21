/*
 * generated by Xtext
 */
package org.xtext.example.mydsl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import org.xtext.example.mydsl.myDsl.Policy
import org.xtext.example.mydsl.myDsl.ReferToRecipientSource
import org.xtext.example.mydsl.myDsl.ReferToRecipientTarget
import org.xtext.example.mydsl.myDsl.RefPrivateData
import org.xtext.example.mydsl.myDsl.Informative
import org.xtext.example.mydsl.myDsl.ReferToService
import org.xtext.example.mydsl.myDsl.Usage
import org.xtext.example.mydsl.myDsl.Retention
import org.xtext.example.mydsl.myDsl.Disclosure
import org.xtext.example.mydsl.myDsl.Collection
import org.xtext.example.mydsl.myDsl.Attribute
import org.xtext.example.mydsl.myDsl.PrivateData
import org.xtext.example.mydsl.myDsl.ServicePartof
import org.xtext.example.mydsl.myDsl.Service
import org.xtext.example.mydsl.myDsl.Partof
import org.xtext.example.mydsl.myDsl.Recipient

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class MyDslGenerator implements IGenerator {
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		fsa.generateFile(resource.className + '.policy', 		
		resource.allContents.filter(typeof(Policy)).map[compilepo].join(' '))    
	}
	
	def className(Resource res) {
	    var name = res.URI.lastSegment
	    return name.substring(0, name.indexOf('.'))
    }
    
    def compilepo(Policy policy)
'''SPEC HEADER
	ATTR NAMESPACE "http://gaius.isri.cmu.edu/example2.owl"
	ATTR DESC "This policy is written to test the use limitation principle"
	«IF !policy.recipient.empty»«FOR x:policy.recipient»«x.compileActor»«ENDFOR»«ENDIF»
	«IF !policy.service.empty»«FOR z:policy.service»«z.compilepurpose»«ENDFOR»«ENDIF»
	«IF !policy.privateData.empty»«FOR y:policy.privateData»«y.compiledetum»«ENDFOR»«ENDIF»
	D ALL-Information > «IF !policy.privateData.empty»«FOR z:policy.privateData SEPARATOR ','»«z.compileall»«ENDFOR»«ENDIF»
SPEC POLICY
	«IF !policy.collection.empty»«FOR x:policy.collection»«x.compilecollection»«ENDFOR»«ENDIF»
	«IF !policy.disclosure.empty»«FOR x:policy.disclosure»«x.compiletransfer»«ENDFOR»«ENDIF»
	«IF !policy.retention.empty»«FOR x:policy.retention»«x.compileretention»«ENDFOR»«ENDIF»
	«IF !policy.usage.empty»«FOR x:policy.usage»«x.compileusage»«ENDFOR»«ENDIF»
	«IF !policy.informative.empty»«FOR x:policy.informative»«x.compileinformative»«ENDFOR»«ENDIF»
'''

def compileall (PrivateData all)
'''«all.privatedata»'''
 
def compileActor (Recipient r)
'''«IF !r.partof.empty»A «r.recipientname» > «FOR part:r.partof SEPARATOR ','»«/*
*/»«part.compilepart»«ENDFOR»«ENDIF»
'''

def compilepart (Partof part)
'''«part.partof.recipientname»''' 

def compilepurpose (Service se)
'''«IF !se.servicepartof.empty»P «se.servicename» > «FOR pur:se.servicepartof SEPARATOR ','»«/*
*/»«pur.compilepurpose»«ENDFOR»«ENDIF»
'''

def compilepurpose (ServicePartof pur)
'''«pur.refertoservice.servicename»'''
 
def compiledetum (PrivateData pd)
'''«IF pd.name != null»D «pd.privatedata» > «FOR pdat:pd.attribute SEPARATOR ','»«pdat.compiledetumat»«ENDFOR»«ENDIF»
'''

def compiledetumat (Attribute pdat)
'''«pdat.name»'''
 
def compilecollection(Collection coll)
'''«IF coll.modalitykind== 'Permission'»P «ELSEIF coll.modalitykind== 'Obligation'»O «ELSE»R «ENDIF»COLLECT «/*
*/»«IF coll.refprivatedata.length== 8»ALL-Information «ELSE»«FOR p:coll.refprivatedata SEPARATOR','»«p.compilep» «ENDFOR»«ENDIF»«/*
*/»«IF !coll.refertoservice.empty»FOR «FOR b:coll.refertoservice SEPARATOR ','»«b.compiles»«ENDFOR»«ELSE»FOR Anything«ENDIF»
'''

def compiletransfer(Disclosure tran)
'''«IF tran.modalitykind== 'Permission'»P «ELSEIF tran.modalitykind== 'Obligation'»O «ELSE»R «ENDIF»TRANSFER «/*
*/»«IF tran.refprivatedata.length== 8»ALL-Information «ELSE»«FOR p:tran.refprivatedata SEPARATOR','»«p.compilep» «ENDFOR»«ENDIF»«/*
*/»«IF !tran.referToRecipientsource.empty»FROM «FOR rs:tran.referToRecipientsource SEPARATOR ','»«rs.compiler»«ENDFOR» «ENDIF»«/*
*/»«IF !tran.referToRecipienttarget.empty»TO «FOR rt:tran.referToRecipienttarget SEPARATOR ','»«rt.compiler»«ENDFOR» «ENDIF»«/*
*/»«IF !tran.refertoservice.empty»FOR «FOR b:tran.refertoservice SEPARATOR ','»«b.compiles»«ENDFOR»«ELSE»FOR Anything«ENDIF»
'''
 
def compileretention(Retention ret)
'''«IF ret.modalitykind== 'Permission'»P «ELSEIF ret.modalitykind== 'Obligation'»O «ELSE»R «ENDIF»RETAIN «/*
*/»«IF ret.refprivatedata.length== 8»ALL-Information «ELSE»«FOR p:ret.refprivatedata SEPARATOR','»«p.compilep» «ENDFOR»«ENDIF»«/*
*/»«IF !ret.refertoservice.empty»FOR «FOR b:ret.refertoservice SEPARATOR ','»«b.compiles»«ENDFOR»«ELSE»FOR Anything«ENDIF»
'''
 
def compileusage(Usage use)
'''«IF use.modalitykind== 'Permission'»P «ELSEIF use.modalitykind== 'Obligation'»O «ELSE»R «ENDIF»USE «/*
*/»«IF use.refprivatedata.length== 8»ALL-Information «ELSE»«FOR p:use.refprivatedata SEPARATOR','»«p.compilep» «ENDFOR»«ENDIF»«/*
*/»«IF !use.refertoservice.empty»FOR «FOR b:use.refertoservice SEPARATOR ','»«b.compiles»«ENDFOR»«ELSE»FOR Anything«ENDIF»
'''
 
def compileinformative(Informative inf)
'''«IF inf.modalitykind== 'Permission'»P «ELSEIF inf.modalitykind== 'Obligation'»O «ELSE»R «ENDIF»INFORM «/*
*/»«IF inf.refprivatedata.length== 8»ALL-Information «FOR p:inf.refprivatedata SEPARATOR','»«p.compilep» «ENDFOR»«ENDIF»«/*
*/»«IF !inf.refertoservice.empty»FOR «FOR b:inf.refertoservice SEPARATOR ','»«b.compiles»«ENDFOR»«ELSE»FOR Anything«ENDIF»
'''

def compiler(ReferToRecipientSource rs) '''«rs.refertore.recipientname»'''

def compiler(ReferToRecipientTarget rt) '''«rt.refertore.recipientname»'''

def compilep(RefPrivateData p) '''«p.refpr.privatedata»'''

def compiles(ReferToService b) '''«b.refertose.servicename»'''

}
