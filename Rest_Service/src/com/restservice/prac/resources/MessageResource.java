package com.restservice.prac.resources;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.cert.X509Certificate;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.xalan.xsltc.runtime.Parameter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.nic.DBUtils.MyConnection;
import com.nic.digitalsign.SignValues;
import com.restservice.prac.model.Message;
import com.restservice.prac.service.MessageService;


@Path("/messages")
public class MessageResource {

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
	
		//System.out.println(map);
        
        
		for(String key:map.keySet())
		{
			
//		System.out.println(key+"--------"+map.get(key));
			
	    filename=getRecordsCount("select filename from filestatus_track where recordId='"+key+"'" , con);
		str=signverfiedXML((String)map.get(key),key,filename,"TEST",con);
		System.out.println(str);
		resultMap.put(filename,str);
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
	
	
	
	
	public String signverfiedXML(String SignContent,String key,String filename,String username,Connection con) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, Exception
    {
        
    String file_status="";  
    String path="F:/xmlfiles/";
    SignValues sign=new SignValues();
  
    
    
   String endResult=new String(xmlsign.Base64Utils.base64Decode(SignContent));
    
    FileUtils.writeStringToFile(new File(path+"/"+filename+".xml"), endResult,"UTF-8");
    
  //  if (xmlsign.XMLVerification.VerifyXML(path+"/"+filename+".xml")) 
//    {
//        System.out.println("**Signature verification Succeeded**"+username);
//       
//        sign.setRecordkey(key);
//        sign.setFilename(key);
//        
//       
//        // file_status =SFTPUtils.sftptransfer("AP001","Test@1234","223.31.62.18",22,"/ePay/ToProcess/",path+"/"+filename+".xml");
//        //System.out.println("***#####################Punjab National PUNE########################**************");    
//        //file_status =SFTPUtils.sftptransfer("hortnet","pnb123","223.31.51.232",22,"/data1/hortnet/NICeSign/Horti/",""+path+"/NICeSign/Horti//"+filename+".byte");
//        //file_status =SFTPUtils.sftptransfer("tscultureven","tscultureeven123","203.189.92.172",22,"/data1/hortnet/IN/",""+path+"/NICeSign/HortiCert//"+username+".cer");
//        
//       
//        //sig.getSignresult().add(sign);
//        updateCertificateDetails(key,xmlsign.XMLVerification.VerifyXMLFileLocation(path+"/"+filename+".xml"),SignContent,"","accepted",key,username,con);
//        //sign.setSigndb(str);  
//    }
//       else {
//        System.out.println("Signature verification FAILED!"+key);
//        
//           }
    
    
    return "success";
    }
	
	
	
	
	
	
	public String updateCertificateDetails(String key,String issuername,String SignedContent,String serialnumber,String bank_status_track,String filename,String username,Connection con) throws Exception 
    {
        
		
		PreparedStatement ps=null;
			
String result="";
String transid="";
try
{
   System.out.println(issuername); 
   ps=con.prepareStatement("update filestatus_track set file_sign_content=?,signed_by=?,tokennum=?,bank_status='accepted',signed_date=now(),bank_status_track=?,bank_sent_date=now() where recordId=? ");
   ps.setString(1,SignedContent);
   ps.setString(2,issuername);
   ps.setString(3,serialnumber);
   ps.setString(4,bank_status_track);
   ps.setString(5,key);
  int j=ps.executeUpdate(); 
  if(j>0)  result="success";
  else  result="failure";

}
catch(Exception e)
{
    System.out.println("***Exception***"+e);  
}   
finally
{
    //rs.close();
   // ps.close();
    //con.close();
}
    
return result;   

    }
	
	
	
	
	
	
	
	
	 public static String getRecordsCount(String sql, Connection con) {
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        String result="";
	        int i = 0;
	        System.out.println("SQL----" + sql);
	        try {
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                result=rs.getString(1);
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
