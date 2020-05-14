package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ems.controller.ConsumerClientController;

@SpringBootApplication
@EnableFeignClients
public class EmpConsumerApplication {

	public static void main(String[] args) {
		ApplicationContext appCtx = SpringApplication.run(EmpConsumerApplication.class, args);
		ConsumerClientController consumerCliController = appCtx.getBean(ConsumerClientController.class);
		consumerCliController.getEmployee();
	}

	@Bean
	public ConsumerClientController consumerCliController() {
		return new ConsumerClientController();

	}
}
