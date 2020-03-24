package com.fileupload.exception;

public class InternalServerException extends RuntimeException{
	
	private static final long serialVersionUID = -935691521264622539L;

	public InternalServerException(String errorCode)
	{
		super(errorCode);
	}

}
