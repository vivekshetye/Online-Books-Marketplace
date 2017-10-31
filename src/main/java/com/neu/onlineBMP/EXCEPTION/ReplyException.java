package com.neu.onlineBMP.EXCEPTION;

public class ReplyException extends Exception {

	public ReplyException(String message)
	{
		super("ReplyException-"+message);
	}
	
	public ReplyException(String message, Throwable cause)
	{
		super("ReplyException-"+message,cause);
	}
	
}
