package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.ems.serviceimpl.ConsumerClientServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerClientController {

	@Autowired
	private ConsumerClientServiceImpl consumerService;

	public void getEmployee() {
		log.debug("Calling getServiceResponse()..!");
		consumerService.getServiceResponse();

	}

}
