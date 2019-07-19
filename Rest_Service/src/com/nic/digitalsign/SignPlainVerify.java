package com.nic.digitalsign;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.security.cert.X509Certificate;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONException;
import com.nic.DBUtils.MyConnection;
import com.restservice.prac.model.Message;
import com.restservice.prac.service.MessageService;
import util.Base64Utils;


@Path("/signing")
public class SignPlainVerify {

	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	
	

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message message) {
//		return messageService.addMessage(message);
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map signMessage(String message) throws JSONException, SQLException
	{
   
		String str="";
		String filename="";
		Map<String,String> resultMap=new HashMap<String,String>();
	    Map<String,String> map=new HashMap<String,String>();
        ObjectMapper mapper=new ObjectMapper();
        MyConnection db=new MyConnection();
        Connection con=null;
	   
        try{
        	
        con=db.getConnection();	 	
        map=mapper.readValue(message,new TypeReference< Map<String,String>> () {});
        String TOKEN_INFO=map.get("TOKEN_INFO");
        map.remove("TOKEN_INFO");
        
        for(String key:map.keySet())
		{
			
    	System.out.println(key+"--------"+map.get(key));
			
	    //filename=getRecordsCount("select filename from filestatus_track where recordId='"+key+"'" , con);
		str=signverfiedPlain(getOriginalString("select regnum,stu_name,course_trngat from regnumbers where regnum='"+key+"' ",con),(String)map.get(key),key,"TEST",con,TOKEN_INFO);
		System.out.println(str);
		resultMap.put(key,str);
	    
		}
		
        }
        catch(Exception e)
        {
         System.out.println("Exception"+e);	
        }
        finally
        {
        	con.close();
        }
		
		return resultMap;
	
	}
	
	
	
	
	public String signverfiedPlain(String OriginalString,String SignContent,String key,String username,Connection con,String TOKEN_INFO) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, Exception
    {
        
    		String DIGITAL_SIGNATURE_ALGORITHM_NAME = "SHA1withRSA";
 	       X509Certificate certificate = null;
	       // ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:/NICeSign/HortiCert/103.cer"));
	       // certificate = (X509Certificate) ois.readObject();
		 
		 
	        ObjectInputStream in = null;
		    ByteArrayInputStream bis = new ByteArrayInputStream(Base64Utils.base64Decode(TOKEN_INFO));
	        in = new ObjectInputStream(bis);
	        certificate = (X509Certificate) in.readObject();

	        
	        
	        
	        
	        
	        
	        SignPlainValues sign = new SignPlainValues();


	        byte[] originalByte = OriginalString.getBytes();
	        Signature verificacion = Signature.getInstance(DIGITAL_SIGNATURE_ALGORITHM_NAME);
	        verificacion.initVerify(certificate.getPublicKey());
	        // System.out.println("SERIAL NUMBER"+ certificate.getSerialNumber()+"ISSUER ALTERNATIVE NAME"+certificate.getIssuerDN());


	        verificacion.update(originalByte);
	        String verficationdata = SignContent;
	        byte[] verfydate = xmlsign.Base64Utils.base64Decode(verficationdata);
	        if (verificacion.verify(verfydate)) {
	            sign.setOriginalstring(OriginalString);
	            sign.setSignstatus("Signature verification Succeeded");
	            sign.setRecordkey(key);
	            sign.setSignedby(certificate.getSubjectDN() + "");
	            //sign.getSignresult().add(sign);
	            updateCertificateDetails(key, certificate.getSubjectDN() + "", SignContent, certificate.getSerialNumber().toString(),con);
	            //sign.setSigndb(str);  




	        } else {
	            //sign.setOriginalString(originalContent);
	            sign.setSignstatus("Signature verification Failed");
	            sign.setRecordkey(key);
	            sign.setSignedby(certificate.getIssuerDN() + "");
	            //sig.getSignresult().add(sign);
	        }


	        return "success";
  
    
    
   
    
    
   
    }
	
	
	
	
	
	
	private void updateCertificateDetails(String key, String issuername, String SignedContent, String serialnumber,Connection con) throws Exception {

       
        PreparedStatement ps = null, ps1 = null;
        ResultSet rs = null;
        String result = "";
        String time = "";
        try {
            ps1 = con.prepareStatement("SELECT ts FROM regnumbers WHERE regnum=?");
            ps1.setString(1, key);
            rs = ps1.executeQuery();
            if (rs.next()) {
                time = rs.getString("ts");
            }
            //Timestamp ts = Timestamp.valueOf(time);
            ps = con.prepareStatement("update regnumbers set digital_sign=?,signed_by=?,tokennum=?,status='accepted',signed_date=now(),certificate='registerapncv.cer',signername=? where regnum=? ");
            ps.setString(1, SignedContent);
            ps.setString(2, issuername);
            ps.setString(3, serialnumber);
            ps.setString(4, "Vedamani");
            ps.setString(5, key);
            int j = ps.executeUpdate();

           


            // if(j>0) retu result="success";
            // else return result="failure";

        } catch (Exception e) {
            System.out.println("Exception" + e);
        } finally {
            //rs.close();
            //ps.close();
            //ps1.close();
            //con.close();
        }

//return result;    



    }
	
	
	
	
	
	
	
	
	 public static String getOriginalString(String sql, Connection con) {
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        String result="";
	        int i = 0;
	        System.out.println("SQL----" + sql);
	        try {
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                result=rs.getString(1) + "&" + rs.getString(2) + "&" + rs.getString(3);
	            }
	            rs.close();

	        } catch (Exception e) {
	            System.out.println("Exception" + e);
	        }

	        return result;

	    }
	
	
	
	
	
	
	
	
	
	
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message message)
//	{
//   
//		return messageService.addMessage(message);
//	
//	}
	
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message gerMessage(@PathParam("id") Long id)
	{
					
		return messageService.getMessage(id);
	}
	
	
//	
//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message getMessage(@PathParam("messageId") long id) {
//		
//		return messageService.getMessage(id);
//		
//	}
	
}
