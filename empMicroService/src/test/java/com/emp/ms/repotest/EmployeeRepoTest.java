package com.emp.ms.repotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emp.ms.model.Employee;
import com.emp.ms.repository.EmployeeRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EmployeeRepoTest {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Test
    public void testSaveEmployee() {

        Employee employee = new Employee();
        employee.setId(4);
        employee.setFname("Amol");
        employee.setLname("Jadhav");
        employee.setCity("Sangamner");
        employee.setState("Maharashtra");
        employee.setMobile(9096637769L);
        
        employeeRepo.save(employee);
      
        Employee emp2 = employeeRepo.findByFname("Amol");
        assertNotNull(employee);
        assertEquals(emp2.getFname(), employee.getFname());
        assertEquals(emp2.getLname(), employee.getLname());
    }
	
	@Test
    public void findAllEmployees() {
       
		Employee employee = new Employee();
        employee.setId(5);
        employee.setFname("Amol");
        employee.setLname("Jadhav");
        employee.setCity("Sangamner");
        employee.setState("Maharashtra");
        employee.setMobile(9096637769L);
        employeeRepo.save(employee);
        
        assertNotNull(employeeRepo.findAll());
    }

}
