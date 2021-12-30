package com.infosys.ftr.workitems.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infosys.ftr.workitems.dto.Message;



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
	@ExceptionHandler(FTRException.class)
	public ResponseEntity<Message> exceptionHandler2(FTRException ex) {
		Message error = new Message();
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
