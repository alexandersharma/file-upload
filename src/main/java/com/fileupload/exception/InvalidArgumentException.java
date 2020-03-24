package com.fileupload.exception;

public class InvalidArgumentException extends RuntimeException{
	
	private static final long serialVersionUID = -935691521264622539L;

	public InvalidArgumentException(String errorCode)
	{
		super(errorCode);
	}

}
