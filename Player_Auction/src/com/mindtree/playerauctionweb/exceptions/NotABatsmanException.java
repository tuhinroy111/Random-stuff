package com.mindtree.playerauctionweb.exceptions;

public class NotABatsmanException extends Exception
{
	String message;

	public NotABatsmanException(String s)
	{
		message=s;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public String getMessage() 
	{
		return message;
	}
}