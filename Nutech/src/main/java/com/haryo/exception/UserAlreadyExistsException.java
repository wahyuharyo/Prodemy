package com.haryo.exception;

public class UserAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 2112294788950136809L;
	
	public UserAlreadyExistsException(String user) {
		super("User "+ user +" already exists");
	}
}
