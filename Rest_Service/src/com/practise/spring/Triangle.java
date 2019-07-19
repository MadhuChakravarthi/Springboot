package com.practise.spring;

public class Triangle {
	
	/**
	 * 
	 */
	private String type="";

	private int label=0;
	
	
	
	
	



//	public void setType(String type) {
//		this.type = type;
//	}
	
	
	public Triangle(String type, int label) {
		super();
		this.type = type;
		this.label = label;
	}


	public String getType() {
		return type;
	}
	
	
	public int getLabel() {
		return label;
	}
	
	public void draw()
	{
		
     System.out.println("*************"+getType()+"***************"+getLabel());		
		
	}
	
	

}
