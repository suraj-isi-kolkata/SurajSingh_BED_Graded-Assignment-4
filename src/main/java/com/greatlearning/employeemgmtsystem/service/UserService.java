package com.greatlearning.employeemgmtsystem.service;

import org.springframework.http.ResponseEntity;

import com.greatlearning.employeemgmtsystem.model.User;

public interface UserService {

	public ResponseEntity<String> addNewUser(User user);
}
