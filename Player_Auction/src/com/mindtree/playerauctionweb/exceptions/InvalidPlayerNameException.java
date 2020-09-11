package com.mindtree.playerauctionweb.exceptions;

public class InvalidPlayerNameException extends Exception {

	String message;
	public InvalidPlayerNameException (String s)
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
