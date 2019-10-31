package com.camel.apacheCamel.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller  // @Controller is used to create the Map of Model object and find the View
				
/*
 *  @RestController is used to simply returns the object and object data is directly written into HTTP response
 *  as JSON or XML 
 */
@RequestMapping("/api")
public class RestControler {
	
	@GetMapping("/camel/{name}")
	public Object getData(@PathVariable(name="name") String f_name,Model model) throws Exception {
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("camel-config.xml");
		
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
		model.addAttribute("map_data", result);
		return "welcome";
		
	}
}
