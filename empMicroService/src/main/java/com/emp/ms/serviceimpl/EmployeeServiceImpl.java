package com.emp.ms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.ms.exception.RecordNotFoundException;
import com.emp.ms.exception.ResourceNotFoundException;
import com.emp.ms.model.Employee;
import com.emp.ms.repository.EmployeeRepo;
import com.emp.ms.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee getEmployeeByID(int empID) {
		log.debug("Inside getEmployeeByID(): ");
		Employee emp = employeeRepo.findById(empID)
				.orElseThrow(() -> new RecordNotFoundException("Employee id \'" + empID + "\' does no exist"));
		return emp;
	}

	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();

	}

	public Employee saveEmployee(Employee e) {
		return employeeRepo.save(e);

	}

	public Employee updateEmployeeByID(Employee newEmp, int empID) {
		Employee e = employeeRepo.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("id", empID, "Employee"));

		e.setCity(newEmp.getCity());
		e.setMobile(newEmp.getMobile());

		Employee updatedEmp = employeeRepo.save(e);
		return updatedEmp;

	}

	public Employee findEmpByName(String name) {
		return employeeRepo.findByFname(name);

	}
}
