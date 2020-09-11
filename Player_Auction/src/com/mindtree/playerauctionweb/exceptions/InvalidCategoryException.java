package com.mindtree.playerauctionweb.exceptions;

public class InvalidCategoryException extends Exception  
{
	String message;
	public InvalidCategoryException (String s)
	{
		message=s;
	}
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}	
}
