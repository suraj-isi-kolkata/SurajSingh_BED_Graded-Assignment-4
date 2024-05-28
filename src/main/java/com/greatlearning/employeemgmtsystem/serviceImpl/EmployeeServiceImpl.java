package com.greatlearning.employeemgmtsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemgmtsystem.model.Employee;
import com.greatlearning.employeemgmtsystem.repository.EmployeeRepository;
import com.greatlearning.employeemgmtsystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	/**
	 * saveRecord() function will check if an employee with the same email is
	 * available. If exists, return a message with 409-Conflict HTTP status. Else,
	 * save the employee and return 201-Created HTTP status.
	 */

	@Override
	public ResponseEntity<String> saveNewEmp(Employee employee) {
		Employee existingEmp = empRepo.findOneByEmail(employee.getEmail());
		if (existingEmp == null) {
			empRepo.save(employee);
			return new ResponseEntity<String>("New Employee added.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Employee  already exists.", HttpStatus.CONFLICT);
		}
	}

	/** getAllEmployees() will list out all the employees available. */

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	/**
	 * getEmpById() will return the employee with the given id or throw an error if
	 * corresponding employee is unavailable.
	 */

	@Override
	public Employee getEmpById(int id) {
		return empRepo.findById(id).get();
	}

	/**
	 * deleteEmpById() will delete an existing employee record based on the id of
	 * the employee.Once deleted, it will return a 200-OK HTTP status. Else returns
	 * a 400-Bad Request HTTP status with a message.
	 */

	@Override
	public ResponseEntity<String> deleteEmpById(int id) {
		try {
			empRepo.deleteById(id);
			String msg = "Deleted employee with id - " + id;
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad request.No employee with given id.", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * updateEmp() will update an existing employee record based on the id of the
	 * employee. Once updated it will return the employee along with 200-OK HTTP
	 * status.Else it will return the input employee with a 400-Bad Request HTTP
	 * status.
	 */

	@Override
	public ResponseEntity<Employee> updateEmp(Employee employee) {
		try {
			Employee emp = empRepo.findById(employee.getId()).get();
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			emp.setEmail(employee.getEmail());
			empRepo.save(employee);
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * getEmpByQuery() will search & fetch an employee by his/her first name and if
	 * found more than one record then list them all.
	 */

	@Override
	public List<Employee> getEmpByQuery(String input_query) {
		Employee empSearch = new Employee();
		empSearch.setFirstName(input_query);
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withIgnorePaths("lastName", "id", "email");
		Example<Employee> example = Example.of(empSearch, customExampleMatcher);
		return empRepo.findAll(example);
	}

	/**
	 * sortByOrder() will list all employee records sorted on their first name in
	 * either ascending order or descending order. If the sort direction input is
	 * wrong, it will return null.
	 **/

	@Override
	public List<Employee> sortByOrder(String order) {
		order = order.substring(1, order.length() - 1);
		if (order.equalsIgnoreCase("ASC")) {
			Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
			return empRepo.findAll(sort);
		} else if (order.equalsIgnoreCase("DESC")) {
			Sort sort = Sort.by(Sort.Direction.DESC, "firstName");
			return empRepo.findAll(sort);
		} else {
			return null;
		}
	}

}
