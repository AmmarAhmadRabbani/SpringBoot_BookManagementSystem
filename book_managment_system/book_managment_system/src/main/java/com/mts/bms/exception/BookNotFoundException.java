package com.mts.bms.exception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String messege) {
		super(messege);
	}

}
