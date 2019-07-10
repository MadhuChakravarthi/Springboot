package xmlsign;
import java.io.File;
//To obtains input bytes from a file in a file system.
//java.security Provides the classes and interfaces for the security framework.
//To get thepublic key
import java.security.PublicKey;
//This class represents certificates as defined in the X.509 standard.
import java.security.cert.X509Certificate;
//This class stand for KeyInfo Element that may contain keys, names, certificates and other public 
//key  management information, such as in-band key distribution or key agreement data. 
import org.apache.xml.security.keys.KeyInfo;
//*the ResourceResolver classes used to resolve ds:Reference URIs.
//This class handles <ds:Signature> elements.
import org.apache.xml.security.signature.XMLSignature;
//Provides all constants and some translation functions.
import org.apache.xml.security.utils.Constants;
//DOM and XML accessibility and comfort functions. 
import org.apache.xml.security.utils.XMLUtils;
//The methods in this class are convenience methods into the low-level XPath API.
import org.apache.xpath.XPathAPI;
//The Element interface represents an element in an HTML or XML document.
import org.w3c.dom.Element;

public class XMLVerification {

    
    
    public static boolean VerifyXML(String FileLocation)
    {
    
Boolean result=false;        
//Path of the Signed file for validation
String signatureFileName = FileLocation;
//String signatureFileName = "/home/otc/Desktop/XMLFILES/signed.xml";

javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
dbf.setNamespaceAware(true);
try {
File f = new File(signatureFileName);
//System.out.println("Trying to verify " + f.toURL().toString());
javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(f));

Element nscontext = XMLUtils.createDSctx(doc,"ds",Constants.SignatureSpecNS);
    
Element sigElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Signature[1]", nscontext);
XMLSignature signature = new XMLSignature(sigElement,f.toURL().toString());
KeyInfo ki = signature.getKeyInfo();
System.out.println("\n key information" +ki);
if (ki != null) {
if (ki.containsX509Data()) {
System.out .println("Could find a X509Data element in the KeyInfo");
}
X509Certificate cert = signature.getKeyInfo().getX509Certificate();
System.out.println("\n Certification"+cert);
if (cert != null) {
System.out.println( "Trying to verify the signature using the X509 Certificate: " + cert);
System.out.println(":::::::cert");
System.out.println("The XML signature in file " + f.toURL().toString() + " is " + (signature.checkSignatureValue(cert)     ? "VALID"  : "INVALID!!!!! "));

result=signature.checkSignatureValue(cert);

} else {
System.out.println("Did not find a Certificate");
PublicKey pk = signature.getKeyInfo().getPublicKey();
System.out.println("\nTo Print public key value "+pk);
if (pk != null) {
System.out.println("Trying to verify the signature using the public key: "   + pk);
System.out.println("The XML signature in file " + f.toURL().toString() + " is " + (signature.checkSignatureValue(pk)  ? "VALID" : "INVALID!!!! "));
} else {
System.out.println("Did not find a public key, so I can't check the signature");
}
}
} else {
System.out.println("Did not find a KeyInfo");
}
} catch (Exception ex) {
 ex.printStackTrace();
}

    
return result;    
}
    
    
    
    
    public static String  VerifyXMLFileLocation(String FileLocation)
    {
    
String result="";        
//Path of the Signed file for validation
String signatureFileName = FileLocation;
//String signatureFileName = "/home/otc/Desktop/XMLFILES/signed.xml";

javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
dbf.setNamespaceAware(true);
try {
File f = new File(signatureFileName);
//System.out.println("Trying to verify " + f.toURL().toString());
javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(f));

Element nscontext = XMLUtils.createDSctx(doc,"ds",Constants.SignatureSpecNS);
    
Element sigElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Signature[1]", nscontext);
XMLSignature signature = new XMLSignature(sigElement,f.toURL().toString());
KeyInfo ki = signature.getKeyInfo();
//System.out.println("\n key information" +ki);
if (ki != null) {
if (ki.containsX509Data()) {
//System.out .println("Could find a X509Data element in the KeyInfo");
}
X509Certificate cert = signature.getKeyInfo().getX509Certificate();
System.out.println("\n Certification");
if (cert != null) {
//System.out.println( "Trying to verify the signature using the X509 Certificate: " + cert);
//System.out.println(":::::::cert");
//System.out.println("The XML signature in file " + f.toURL().toString() + " is " + (signature.checkSignatureValue(cert)     ? "VALID"  : "INVALID!!!!! "));

//result=signature.checkSignatureValue(cert);
result=cert.getSubjectDN().toString();
} else {
//System.out.println("Did not find a Certificate");
PublicKey pk = signature.getKeyInfo().getPublicKey();
//System.out.println("\nTo Print public key value "+pk);
if (pk != null) {
//System.out.println("Trying to verify the signature using the public key: "   + pk);
//System.out.println("The XML signature in file " + f.toURL().toString() + " is " + (signature.checkSignatureValue(pk)  ? "VALID" : "INVALID!!!! "));
} else {
System.out.println("Did not find a public key, so I can't check the signature");
}
}
} else {
System.out.println("Did not find a KeyInfo");
}
} catch (Exception ex) {
 ex.printStackTrace();
}

    
return result;    
}   
    
    
    
    
    
    
    
    
    
    
static {
org.apache.xml.security.Init.init();
}
}