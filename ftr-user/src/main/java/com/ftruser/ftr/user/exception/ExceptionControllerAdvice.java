package com.ftruser.ftr.user.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ftruser.ftr.user.DTO.Message;


@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	Environment env;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Message> exceptionHandler(Exception ex) {
		Message error = new Message();
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Message> exceptionHandler2(UserNotFoundException ex) {
		Message error = new Message();
		error.setMessage(env.getProperty(ex.getMessage()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
