package com.restservive.practise;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonArray;
@Path("/result")
public class RestService {

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MessageService getDetails()
	{
		
  MessageType mt=new MessageType("subelement", "name of the subelement");  
  MessageType mt1=new MessageType("subelement1", "name of the subelement1");  
  List<MessageType> mt2=new ArrayList<>();	
  mt2.add(mt);
  mt2.add(mt1);
  MessageService ms=	new MessageService("1", "2", "3",mt2);
  return ms;
	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageService addDetails(MessageService ms) throws Exception
	{
		
		
		  System.out.println(ms.getCreatedBy()+"-"+ms.getMessageContent());  
		  
		  
		  List<MessageType> test =  ms.getMessageType();
		  for(MessageType mt : test)
		  {
			  
			  System.out.println("3"+mt.getMessage_type_code()+"4"+mt.getMessage_type_name());
		  }
		
		
//		JSONObject object = new JSONObject(ms);
//		
//		
//		//JSONObject jsonObject = jsonArray.getJSONObject(1);
//		
//		
//		JSONArray getArray = object.getJSONArray("messageType");
//
//		String result="";
//		for (int i=0;i<getArray.length();i++)
//		{
//		JSONObject objects = getArray.getJSONObject(i);
//		result=result+objects.get("message_type_code");
//		
//		}
//		
//		return result;
	
	return ms;
	
	}
	
	
	
}
