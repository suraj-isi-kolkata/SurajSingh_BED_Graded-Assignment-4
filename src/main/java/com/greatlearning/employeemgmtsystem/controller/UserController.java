package com.greatlearning.employeemgmtsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemgmtsystem.model.User;
import com.greatlearning.employeemgmtsystem.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService service;

	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<String> saveNewRole(@RequestBody User user) {
		return service.addNewUser(user);
	}
}
