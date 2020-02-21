package com.camel.apacheCamel.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController  // @Controller is used to create the Map of Model object and find the View
				
/*
 *  @RestController is used to simply returns the object and object data is directly written into HTTP response
 *  as JSON or XML 
 */
public class RestControler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestControler.class);
	
	@GetMapping("/camel")
	public Object getData(Model model) throws Exception {
		LOGGER.debug("Entered in getData() of RestController..");
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

	@GetMapping("/")
	public String homePage() {
		return "home";
		
	}
	
	@GetMapping("/getJSONdata")
	public Map<String,Object> getJSONData() {
		
		Map<String,Object>map=new HashMap<>();
		map.put("error", "No");
		map.put("errorCode", "200");
		map.put("message", "Everything is Fine..!!");
		
		return map;
	}
	
	@GetMapping("/sendMail")
	public void sendMail() {
		LOGGER.info("Entered into sendMail() method of ResContoller..");
		String recipient ="pradip.nikam@capgemini.com";
		String sender ="pradip.nikam@capgemini.com";
		String host="localhost";
		Properties properties=System.getProperties();
		
		properties.setProperty("mail.smtp.port", host);
		
		Session session = Session.getDefaultInstance(properties);
		 try 
	      { 
	         // MimeMessage object. 
	         MimeMessage message = new MimeMessage(session); 
	  
	         // Set From Field: adding senders email to from field. 
	         message.setFrom(new InternetAddress(sender)); 
	  
	         // Set To Field: adding recipient's email to from field. 
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
	  
	         // Set Subject: subject of the email 
	         message.setSubject("This is Suject"); 
	  
	         // set body of the email. 
	         message.setText("This is a test mail"); 
	  
	         // Send email. 
	         Transport.send(message); 
	         System.out.println("Mail successfully sent"); 
	      } 
	      catch (MessagingException mex)  
	      { 
	         mex.printStackTrace(); 
	      } 
		
		LOGGER.info("Existing from sendMail() method of ResContoller..");

	}
}
