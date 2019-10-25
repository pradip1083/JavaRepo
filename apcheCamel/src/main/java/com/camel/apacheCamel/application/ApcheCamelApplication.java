package com.camel.apacheCamel.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class ApcheCamelApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApcheCamelApplication.class, args);
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext-camel.xml");
		
		CamelContext camelcontext = SpringCamelContext.springCamelContext(appContext, false);
		ProducerTemplate template = camelcontext.createProducerTemplate();
		
		Map<String,String>map=new HashMap<>();
		map.put("f_name", "Pradip");
		map.put("l_name", "Nikam");
		map.put("city", "Sangamner");
		map.put("state", "Maharashtra");
		
		Map<String,String>headers=new HashMap<>();
		headers.put("bb", "BroadBand");
		headers.put("pstn", "LandLine");
		headers.put("bt tv", "BTTV");
		
		List<String>result=template.requestBodyAndHeader("direct:broadband",map, "header", headers, List.class);
		
		System.out.println("The Final Result of camel output:.... "+result);
			
	}

}
