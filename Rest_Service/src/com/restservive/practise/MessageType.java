package com.restservive.practise;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;


public class MessageType {
	
	private String message_type_code;
	private String message_type_name;
	
	public MessageType()
	{
		
	}
	
	
	public MessageType(String message_type_code, String message_type_name) {
		
		this.message_type_code = message_type_code;
		this.message_type_name = message_type_name;
	}
	public String getMessage_type_code() {
		return message_type_code;
	}
	public void setMessage_type_code(String message_type_code) {
		this.message_type_code = message_type_code;
	}
	public String getMessage_type_name() {
		return message_type_name;
	}
	public void setMessage_type_name(String message_type_name) {
		this.message_type_name = message_type_name;
	}

}
