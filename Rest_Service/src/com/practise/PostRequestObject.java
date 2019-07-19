package com.practise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.restservice.prac.model.Message;
import com.restservive.practise.MessageService;
import com.restservive.practise.MessageType;

public class PostRequestObject {

	
	  private final static String USER_AGENT = "Mozilla/5.0";
	  
	  
	public static void main(String args[]) throws Exception
	{
	
	String url = "http://localhost:8080/Rest_Service/rest/result/";

	HttpClient client = HttpClientBuilder.create().build();
	//HttpPost post = new HttpPost(url);

	// add header
	//post.setHeader("User-Agent", USER_AGENT);

	 MessageType mt=new MessageType("subelement", "name of the subelement");  
	 MessageType mt1=new MessageType("subelement1", "name of the subelement1");  
	 List<MessageType> mt2=new ArrayList<>();	
	 mt2.add(mt);
	 mt2.add(mt1);
	 MessageService ms=	new MessageService("1", "2", "3",mt2);

//	post.setEntity(entity);
	 
	  //  Client client = ClientBuilder.newClient();
	   // String strResult = client.target(url).request(MediaType.APPLICATION_XML).put(Entity.xml(ms), String.class);
	}
	
	
}
