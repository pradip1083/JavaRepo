package com.emp.ms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private int fieldValue;
	private String fieldName;
	private String resourceName;
	public ResourceNotFoundException(String fieldName, int fieldValue, String resourceName) {
		
		super(String.format("%s is not found with %s: '%s'", resourceName,fieldName,fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.resourceName = resourceName;
	}
	


}
