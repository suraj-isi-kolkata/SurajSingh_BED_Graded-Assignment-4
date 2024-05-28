package com.greatlearning.employeemgmtsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeemgmtsystem.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findOneByName(String name);

}
