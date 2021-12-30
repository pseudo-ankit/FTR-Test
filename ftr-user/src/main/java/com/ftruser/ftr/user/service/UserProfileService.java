package com.ftruser.ftr.user.service;
import com.ftruser.ftr.user.DTO.UserProfileDTO;
import com.ftruser.ftr.user.controller.UserProfileController;
import com.ftruser.ftr.user.entity.User;
import com.ftruser.ftr.user.exception.UserNotFoundException;
import com.ftruser.ftr.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(UserProfileService.class);

	
	public UserProfileDTO createUser( UserProfileDTO dto){
		logger.info("createUser service method");
		User user=UserProfileDTO.createEntity(dto);
		user=userRepository.save(user);
		return User.createDTO(user);
		
	}
	
	public String updateUser(int userId,  UserProfileDTO userDto) throws UserNotFoundException {
		Optional<User> user=userRepository.findById(userId);
		logger.info("updateUser service method");
		User obj;
		if(user.isPresent()) {
			obj=UserProfileDTO.createEntity(userDto);
			obj=userRepository.save(obj);
			return env.getProperty("user.update.success")+userId;
		}else {
			throw new UserNotFoundException("user.notFound");
		}
	}
	
	public String deleteUser( int userId) throws UserNotFoundException {
		logger.info("deleteUser service method");
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			userRepository.deleteById(userId);
			return env.getProperty("user.delete.success")+userId;
		}else {
			throw new UserNotFoundException("user.notFound");
		}
	}
	
	public UserProfileDTO getUser(int userId) throws UserNotFoundException {
		logger.info("getUser service method");
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			return User.createDTO(user.get());
		}else {
			throw new UserNotFoundException("user.notFound");
		}
	}
	
	public boolean login(int userid,String pwd) {
		logger.info("login service method");
		Optional<User> user=userRepository.findById(userid);
		if(user.isPresent())
		{
			if(user.get().getPassword().equals(pwd)) {
				return true;
			}
			else {
				return false;
			}
		}return false;
	}
	
	public List<UserProfileDTO> getAllUser()  {
		logger.info("getAllUser service method");
		List<User> user=userRepository.findAllByOrderByUserId();
		List<UserProfileDTO> lst= new ArrayList<>();
		for(User u:user) {
			UserProfileDTO obj=User.createDTO(u);
			lst.add(obj);
		}
		return lst;
	}



}
