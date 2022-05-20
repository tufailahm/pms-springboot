package com.revature.pms.exeptions;

public class LoginException extends RuntimeException{

	public LoginException() {
	}
	
	public LoginException(String message) {
		super(message);
	}
}
