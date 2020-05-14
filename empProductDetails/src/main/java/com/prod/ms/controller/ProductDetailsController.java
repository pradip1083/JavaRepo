package com.prod.ms.controller;

import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productDetails")
public class ProductDetailsController {

	@GetMapping(value="/getProduct", produces=MediaType.APPLICATION_JSON_VALUE)
	@Produces("application/json")
	public String getProduct() {
		return "Samsung-Mobile-G567";
	}
}
