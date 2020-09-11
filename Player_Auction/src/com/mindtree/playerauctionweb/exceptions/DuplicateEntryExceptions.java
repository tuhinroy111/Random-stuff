package com.mindtree.playerauctionweb.exceptions;

public class DuplicateEntryExceptions extends Exception
{
	String message;
	public DuplicateEntryExceptions (String s)
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