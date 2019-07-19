package com.practise;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.restservive.practise.MessageService;
import com.restservive.practise.MessageType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PlainRestClient {
	
	public static void main(String[] args) {
		
	
	
	try {
		 MessageType mt=new MessageType("element", "name of the subelement1");  
		 MessageType mt1=new MessageType("elemen41", "name of the subelement2");  
		 List<MessageType> mt2=new ArrayList<>();	
		 mt2.add(mt);
		 mt2.add(mt1);
		 MessageService ms=	new MessageService("Madhu", "Chakravrthi", "Test",mt2);
		 
		 System.out.println("TEST MESSAGE SERVICE"+ms);	 
		
        Client client = Client.create();    
        WebResource resource = client.resource("http://localhost:8080/Rest_Service/rest/result/");    
          
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type("application/json").post(
                ClientResponse.class,ms);
        
        
        
      } catch (Exception e) {    
              e.printStackTrace();    
      }
 

}
}