package com.mts.bms.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mts.bms.exception.BookNotFoundException;
import com.mts.bms.exception.CategoryNotFoundException;
import com.mts.bms.exception.RoleNotFoundException;
import com.mts.bms.exception.UserNotFoundException;
import com.mts.bms.response.SuccessResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<SuccessResponse> categoryNotFoundException(CategoryNotFoundException exception) {
		return new ResponseEntity<>(new SuccessResponse(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<SuccessResponse> bookNotFoundException(BookNotFoundException exception) {
		return new ResponseEntity<>(new SuccessResponse(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = RoleNotFoundException.class)
	public ResponseEntity<SuccessResponse> roleNotFoundException(RoleNotFoundException exception) {
		return new ResponseEntity<>(new SuccessResponse(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<SuccessResponse> userNotFoundException(UserNotFoundException exception) {
		return new ResponseEntity<>(new SuccessResponse(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

}
