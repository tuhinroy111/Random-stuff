package com.mindtree.playerauctionweb.exceptions;

public class InvalidTeamNameException extends Exception
{
	String message;

	public InvalidTeamNameException(String s)
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
