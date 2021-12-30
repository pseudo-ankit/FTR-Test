package com.ftruser.ftr.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftruser.ftr.user.service.UserProfileService;
import com.ftruser.ftr.user.DTO.Message;
import com.ftruser.ftr.user.DTO.UserProfileDTO;
import com.ftruser.ftr.user.exception.UserNotFoundException;

@RestController
@RequestMapping("/userProfile")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserProfileController {

	private static Logger logger = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserProfileDTO> createUser(@RequestBody @Valid UserProfileDTO dto, Errors error) throws Exception {
		logger.info("CreateUser Controller method");
		String response = "";
		if (error.hasErrors()) {
			response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
        throw new Exception(response);
		} else {
			dto = userProfileService.createUser(dto);
			return ResponseEntity.ok(dto);
		}
	}

	@GetMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<UserProfileDTO> getUser(@PathVariable("userId") int userId) throws UserNotFoundException {
		logger.info("getUser Controller method");
		UserProfileDTO dto = userProfileService.getUser(userId);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Message> updateUser(@PathVariable("userId") int userId,
			@RequestBody @Valid UserProfileDTO userDto, Errors error) throws UserNotFoundException {
		logger.info("updateUser Controller method");
		String response = "";
		if (error.hasErrors()) {
			response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			return new ResponseEntity<Message>(new Message(response), HttpStatus.BAD_REQUEST);
		} else {
			String str = userProfileService.updateUser(userId, userDto);
			return ResponseEntity.ok(new Message(str));
		}
	}

	@DeleteMapping(value="{userId}")
	public ResponseEntity<Message> deleteUser(@PathVariable("userId") int userId) throws UserNotFoundException{
		logger.info("deleteUser Controller method");
		String str=userProfileService.deleteUser(userId);
		return ResponseEntity.ok(new Message(str));
	}
	
	@GetMapping(value = "/{userId}/{password}", produces = "application/json")
	public ResponseEntity<Message> login(@PathVariable("userId") int userId,@PathVariable("password") String password)  {
		logger.info("getUser Controller method");
		boolean bool = userProfileService.login(userId, password);
		return ResponseEntity.ok(new Message(String.valueOf(bool)));
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<UserProfileDTO>> allUser()  {
		logger.info("allUser Controller method");
		List<UserProfileDTO> lst = userProfileService.getAllUser();
		return ResponseEntity.ok(lst);
	}


}
