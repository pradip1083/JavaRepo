package com.emp.ms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.ms.model.Employee;
import com.emp.ms.serviceimpl.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/microService")
@Slf4j
@ControllerAdvice
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping(value = "/getEmployee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployee(@PathVariable(value = "id") int empID) {
		log.debug("Inside getEmployee() with Employee ID: " + empID);
		return employeeService.getEmployeeByID(empID);

	}

	@GetMapping(value = "/getAllEmployee", produces = MediaType.APPLICATION_JSON_VALUE)

	public List<Employee> getAllEmployee() {
		log.debug("Inside getAllEmployee(): ");
		return employeeService.getAllEmployee();
	}

	@PostMapping(value = "/SaveEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee saveEmployee(@Valid @RequestBody Employee emp) {
		log.debug("Inside saveEmployee(): " + emp.toString());
		return employeeService.saveEmployee(emp);
	}

	@PutMapping(value = "/updateEmployee/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee updateEmployeeByID(@PathVariable(value = "id") int empID, @Valid @RequestBody Employee empDetails) {
		log.debug("Inside updateEmployee(): " + empDetails.toString());
		return employeeService.updateEmployeeByID(empDetails, empID);
	}

	@GetMapping("/getEmployeeByFname/{fname}")
	public Employee getEmpCity(@PathVariable(value = "fname") String firstName) {

		return employeeService.findEmpByName(firstName);
	}

}