package com.greatlearning.employeemgmtsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.greatlearning.employeemgmtsystem.model.Employee;

public interface EmployeeService {

	public ResponseEntity<String> saveNewEmp(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getEmpById(int id);

	public ResponseEntity<String> deleteEmpById(int id);

	public List<Employee> getEmpByQuery(String input_query);

	public ResponseEntity<Employee> updateEmp(Employee employee);

	public List<Employee> sortByOrder(String order);

}
