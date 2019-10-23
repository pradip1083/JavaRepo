package com.example.notes.Notes.model;

import java.util.Date;

public class HelloWorld {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void init() {
		System.out.println("Bean is going to be initialize...");
		
	}
	
	public void destroy() {
		System.out.println("Bean is going to be destroyed..");
	}
	
	
}
