package com.ftruser.ftr.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftruser.ftr.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findAllByOrderByUserId();

}
