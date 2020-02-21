package com.ems.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ems.feignclient.RemoteCallClientInterface;
import com.ems.model.Employee;
import com.ems.service.ConsumerClientService;
import com.ems.util.ConsumerConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerClientServiceImpl implements ConsumerClientService {

	@Autowired
	private RemoteCallClientInterface feignLoadBalancer;

	@Autowired
	private LoadBalancerClient loadBalancer;

	public void getServiceResponse() {

		log.info("Inside getSericeResponse()..!");
		ServiceInstance serviceInstance = loadBalancer.choose("employee.producer");
		String baseEmpURL = serviceInstance.getUri().toString();
		log.debug("Full URL of Employee service: {}", baseEmpURL + ConsumerConstant.GETEMP_BY_ID_URL);
		String url = baseEmpURL + ConsumerConstant.GETEMP_BY_ID_URL;
		getResponse(url);

		ServiceInstance productInstances = loadBalancer.choose("emp.product.details");
		log.debug("List of product instances are: " + productInstances);
		String baseProductURL = productInstances.getUri().toString();
		log.debug("Full URL of Employee service: {}", baseProductURL + ConsumerConstant.GET_PROD_DETAILS);
		String produtDetailsUrl = baseProductURL + ConsumerConstant.GET_PROD_DETAILS;
		getResponse(produtDetailsUrl);

		Employee e = feignLoadBalancer.getEmployeeData();
		log.debug(" Product details using Feign are: " + e.toString());

	}

	public static void getResponse(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;

		try {
			response = restTemplate.exchange(url, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception e) {
			log.error("Exception occured in getResponse(): {}", e.getMessage());
		}
		if (null != response) {
			log.debug("Final Response: {}", response.getBody().toString());
		}
	}

	private static HttpEntity<?> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
