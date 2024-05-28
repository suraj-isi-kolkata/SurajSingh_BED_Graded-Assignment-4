package com.greatlearning.employeemgmtsystem.service;

import org.springframework.http.ResponseEntity;

import com.greatlearning.employeemgmtsystem.model.Role;

public interface RoleService {

	public ResponseEntity<String> addNewRole(Role role);

}
