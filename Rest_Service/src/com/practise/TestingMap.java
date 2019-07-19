package com.practise;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import com.restservice.prac.model.Message;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

public class TestingMap {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Map<Long, Message> messages=new HashMap<Long,Message>();

		messages.put(1L, new Message(1, "Hello World", "Madhu"));
		messages.put(2L, new Message(2, "Hello World", "Sudheer"));
		
		List<Message> messageEnd=getAllMessages(messages); 
		
		for(Message test:messageEnd)
		{
		 System.out.println(test.getId()+"---"+test.getAuthor());
		}
		
		
		//System.out.println(messages.get(1L));
	}
	
	public static List<Message> getAllMessages(Map<Long, Message> messages)
	{
	    System.out.println(messages.values());
		return new ArrayList<Message>(messages.values());
	
	}

}
