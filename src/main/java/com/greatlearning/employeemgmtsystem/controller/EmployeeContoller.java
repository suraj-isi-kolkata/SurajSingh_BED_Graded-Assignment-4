package com.greatlearning.employeemgmtsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemgmtsystem.model.Employee;
import com.greatlearning.employeemgmtsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeContoller {

	@Autowired
	EmployeeService service;

	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<String> saveNewEmployee(@RequestBody Employee employee) {
		return service.saveNewEmp(employee);
	}

	@GetMapping(value = "/listOfEmployees")
	public List<Employee> listAllEmp() {
		return service.getAllEmployees();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Employee getEmpById(Model model, @PathVariable("id") int id) {
		return service.getEmpById(id);
	}

	@PostMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
		return service.updateEmp(employee);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteEmpById(Model model, @PathVariable("id") int id) {
		return service.deleteEmpById(id);
	}

	@GetMapping(value = "/search/{query}")
	public List<Employee> getEmpByQuery(@PathVariable String query, Model model) {
		return service.getEmpByQuery(query);
	}

	@GetMapping(value = "/sort")
	public List<Employee> sortByOrder(@RequestParam String order, Model model) {
		return service.sortByOrder(order);
	}

}
