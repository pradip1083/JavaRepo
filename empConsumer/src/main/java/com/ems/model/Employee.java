package com.ems.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Employee {

	private  long	 id;
	private  String fname;
	private  String lname;
	private  String city;
	private  String state;
	private  long 	mobile;
	
}
