package com.bank.exceptions;

import org.json.JSONException;

public class LoginException extends Exception {

	public LoginException(String message) {
		
		super(message);
		// TODO Auto-generated constructor stub
		message = "<p> Incorrect username or password. Please try to login again </p>";
	}

}
