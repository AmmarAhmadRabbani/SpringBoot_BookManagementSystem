package com.mts.bms.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String messege) {
		super(messege);
	}

}
