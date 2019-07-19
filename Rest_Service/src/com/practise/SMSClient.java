package com.practise;

import javax.ws.rs.core.MediaType;
import com.nic.sms.beans.SMSPojo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SMSClient {
	
	public static void main(String[] args) {
		
	
	
	try {
		
		SMSPojo smsPojo=new SMSPojo("TEST MESSAGE","9989274858",1);
		Client client = Client.create();    
        
		WebResource resource = client.resource("http://localhost:8080/RestExample/rest/smsservice");    
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type("application/json").post(
                ClientResponse.class,smsPojo);
        
        //System.out.println("Response"+response.getEntity(String.class));
        
      } catch (Exception e) {    
    	  
    	      System.out.println(e);
              e.printStackTrace();    
      }
 

	}
}