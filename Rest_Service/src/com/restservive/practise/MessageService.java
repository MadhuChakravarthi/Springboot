package com.restservive.practise;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class MessageService {
	
	
	private String messageId="";
	private String createdBy="";
	private String messageContent="";
	List<MessageType> messageType=new ArrayList<MessageType>();
	
  MessageService()
	{
		
	}
	

	
	public MessageService(String messageId, String createdBy, String messageContent,List<MessageType> listMessageType) {
		
		this.messageId = messageId;
		this.createdBy = createdBy;
		this.messageContent = messageContent;
		this.messageType=listMessageType;
	}
	
	
	public List<MessageType> getMessageType() {
		return messageType;
	}


	public void setMessageType(List<MessageType> messageType) {
		this.messageType = messageType;
	}


	
	
	public String getMessageId() {
		return messageId;
	}



	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}



	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


}
