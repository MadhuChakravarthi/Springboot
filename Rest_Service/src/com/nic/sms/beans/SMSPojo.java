package com.nic.sms.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SMSPojo {
	
	
//	private String username="",password="",senderid="",URL="",projectID="",Connection="";
	
	private String message="";
	
	private String mobileno="";
	
	private int projectID=0;
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public SMSPojo()
	{
		
	}

	public SMSPojo(String message, String mobileno,int projectID) {
		super();
		this.message = message;
		this.mobileno = mobileno;
		this.projectID=projectID;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getConnection() {
		return Connection;
	}

	public void setConnection(String connection) {
		Connection = connection;
	}*/
	
	

}
