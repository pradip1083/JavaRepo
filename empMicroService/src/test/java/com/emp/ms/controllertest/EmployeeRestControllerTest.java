
package com.emp.ms.controllertest;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emp.ms.controller.EmployeeController;
import com.emp.ms.exception.RecordNotFoundException;
import com.emp.ms.model.Employee;
import com.emp.ms.serviceimpl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeRestControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController emplocontroller;

	@Mock
	private EmployeeServiceImpl service;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(emplocontroller).setControllerAdvice(emplocontroller).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetEmployee() throws Exception {

		Employee e = new Employee();
		e.setFname("Pradip");
		e.setId(1);
		e.setLname("Nikam");
		e.setMobile(9552231763L);
		e.setState("Maharashtra");

		Mockito.when(service.getEmployeeByID(1)).thenReturn(e);

		MvcResult mvcResult = mockMvc
				.perform(get("/microService/getEmployee/{id}", 1).contentType("application/json")
						.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk()).andReturn();

		String response = mvcResult.getResponse().getContentAsString();
		Employee ee = new ObjectMapper().readValue(response, Employee.class);

		Assert.assertEquals("Pradip", ee.getFname());

	}

	@Test
	public void testGetEmployee_checkFor_RecordNotFoundException_Exception() throws Exception {

		Mockito.when(service.getEmployeeByID(6))
				.thenThrow(new RecordNotFoundException("Employee id \'" + 6 + "\' does no exist"));

		mockMvc.perform(get("/microService/getEmployee/{id}", 6).contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

		Mockito.verify(service, times(1)).getEmployeeByID(6);

	}

	@Test
	public void testSaveEmployee() throws Exception {
		Employee e = new Employee();

		e.setFname("sushant");
		e.setLname("varpe");
		e.setCity("Sangamner");
		e.setMobile(9552125454L);
		e.setState("maharashtra");

		Gson gson = new Gson();
		String jsonBody = gson.toJson(e);

		Mockito.when(service.saveEmployee(e)).thenReturn(e);

		mockMvc.perform(post("/microService/SaveEmployee").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				.andExpect(status().isOk());

	}

}
