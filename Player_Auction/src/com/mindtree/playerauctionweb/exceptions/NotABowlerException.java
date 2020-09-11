package com.mindtree.playerauctionweb.exceptions;

public class NotABowlerException extends Exception
{
	String message;

	public NotABowlerException(String s)
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