package com.ems.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ems.model.Employee;

@FeignClient(name = "employee.producer")
public interface RemoteCallClientInterface {

	@RequestMapping(value = "/microService/getEmployee/1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeData();

}
