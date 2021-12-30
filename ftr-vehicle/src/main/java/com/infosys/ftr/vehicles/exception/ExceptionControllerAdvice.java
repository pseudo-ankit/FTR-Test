package com.infosys.ftr.vehicles.exception;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infosys.ftr.vehicles.dto.Message;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	private Environment environment;
	
	@Value("${vehicle.notFound}")
	private String notFoundMessage;
	
	@Value("${vehicle.update.alreadyExists}")
	private String alreadyExistsMessage;
	
	Message error;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Message> exceptionHandler(Exception ex) {
		
		error = new Message();
		error.setMessage(environment.getProperty(ExceptionConstants.SERVER_ERROR.toString()));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(VehicleFtrException.class)
	public ResponseEntity<Message> exceptionHandler(VehicleFtrException ex) {
		
		error = new Message();
		error.setMessage(environment.getProperty(ex.getMessage()));
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<Message> exceptionHandler(){
		error = new Message();
		error.setMessage(notFoundMessage);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
		
	@ExceptionHandler(VehichleStatusExistException.class)
	public ResponseEntity<Message> exceptionHandler(VehichleStatusExistException ex){
		error = new Message();
		error.setMessage(alreadyExistsMessage+" "+ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Message> processValidationError(MethodArgumentNotValidException ex) {
		error = new Message();
		BindingResult result = ex.getBindingResult();
                
        error.setMessage(result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	

}
