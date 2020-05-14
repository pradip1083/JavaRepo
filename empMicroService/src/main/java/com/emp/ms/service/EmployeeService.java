package com.emp.ms.service;

import java.util.List;

import com.emp.ms.model.Employee;

public interface EmployeeService {

	public Employee getEmployeeByID(int id);

	public List<Employee> getAllEmployee();

	public Employee saveEmployee(Employee e);

	public Employee updateEmployeeByID(Employee newEmp, int empID);

	public Employee findEmpByName(String name);

}
