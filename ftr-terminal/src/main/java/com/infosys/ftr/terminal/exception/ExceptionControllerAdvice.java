package com.infosys.ftr.terminal.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infosys.ftr.terminal.dto.Message;


@RestControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	Environment env;

	// This method is used to handle general exceptions and returns exception
	// message.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Message> exceptionHandler(Exception ex) {
		Message error = new Message();
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// This method is used to handle WecareExceptions and returns ErrorMessage
	// object with status OK.
	@ExceptionHandler(FTRException.class)
	public ResponseEntity<Message> exceptionHandler(FTRException e) {
		Message error = new Message();
		error.setMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Message> exceptionHandler(DataIntegrityViolationException e) {
		Message error = new Message();
		error.setMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// validation failures on DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Message> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Message error = new Message();
		error.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", ")));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// Validation failures on URI parameters
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Message> handleConstraintValidationExceptions(ConstraintViolationException ex) {
		Message error = new Message();
		error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(", ")));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
