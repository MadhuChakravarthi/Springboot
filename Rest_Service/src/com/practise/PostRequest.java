package com.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.restservice.prac.model.Message;
import com.restservive.practise.MessageService;
import com.restservive.practise.MessageType;

public class PostRequest {

	public static void main(String[] args) {

	  try {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("http://localhost:8080/Testrestmaven/rest/result/");

//		Message message=new Message();
//		message.setId(3444444449L);
//		message.setAuthor("testting");
//		message.setMessage("RRRRRRRRRRRRR");
		
		 MessageType mt=new MessageType("subelement", "name of the subelement");  
		 MessageType mt1=new MessageType("subelement1", "name of the subelement1");  
		 List<MessageType> mt2=new ArrayList<>();	
		 mt2.add(mt);
		 mt2.add(mt1);
		 MessageService ms=	new MessageService("1", "2", "3",mt2);
	     Gson gson=new Gson();
	     String request = gson.toJson(ms);
		 StringEntity input = new StringEntity(request);
    	 input.setContentType("application/json");
		 postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

		

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println("TEST"+output);
		}

		httpClient.getConnectionManager().shutdown();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}

}