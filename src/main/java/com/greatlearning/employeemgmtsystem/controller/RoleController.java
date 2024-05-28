package com.greatlearning.employeemgmtsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemgmtsystem.model.Role;
import com.greatlearning.employeemgmtsystem.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	RoleService service;

	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<String> saveNewRole(@RequestBody Role role) {
		return service.addNewRole(role);
	}

}
