package com.emp.ms.servicetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.emp.ms.model.Employee;
import com.emp.ms.repository.EmployeeRepo;
import com.emp.ms.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeServiceImpl empService;

	@Mock
	private EmployeeRepo empRepo;

	@Test
	public void testGetAllEmployee() throws Exception {

		List<Employee> list = new ArrayList<>();
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		e1.setFname("Pradip");
		e1.setId(1);
		e1.setLname("Nikam");
		e1.setMobile(9552231763L);
		e1.setState("Maharashtra");

		e2.setFname("Pradip");
		e2.setId(1);
		e2.setLname("Nikam");
		e2.setMobile(9552231763L);
		e2.setState("Maharashtra");

		list.add(e1);
		list.add(e2);

		Mockito.when(empRepo.findAll()).thenReturn(list);

		List<Employee> empList = empService.getAllEmployee();

		Assert.assertEquals("Pradip", empList.get(0).getFname());

	}

	@Test
	public void testGetEmployeeById() throws Exception {
		Employee e1 = new Employee();
		e1.setFname("Pradip");
		e1.setId(1);
		e1.setLname("Nikam");
		e1.setCity("Sangamner");
		e1.setMobile(9552231763L);
		e1.setState("Maharashtra");

		Mockito.when(empRepo.findById(1)).thenReturn(Optional.of(e1));

		Employee emp = empService.getEmployeeByID(1);

		Assert.assertEquals("Pradip", emp.getFname());

	}

}
