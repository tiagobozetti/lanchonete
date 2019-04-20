package com.finch.burguer.services.exceptions;

public class AuthorizationExceptionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthorizationExceptionException(String msg) {
		super(msg);
	}
	
	public AuthorizationExceptionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
