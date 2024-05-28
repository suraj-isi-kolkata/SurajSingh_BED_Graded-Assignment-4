package com.greatlearning.employeemgmtsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeemgmtsystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findOneByEmail(String email);

}
