package com.camel.apacheCamel.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PSTNHealthCheckProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,String>headers= exchange.getIn().getHeader("header",Map.class);
		List<String>list=new ArrayList();
		
		System.out.println("Inside PSTNHealthCheckProcessor "+headers.get("pstn"));
		Map<String,String>mapContent=(Map)exchange.getProperty("map");
		
		
		list.add(mapContent.get("f_name"));
		list.add(mapContent.get("l_name"));
		list.add(mapContent.get("city"));
		list.add(mapContent.get("state"));
		
		exchange.getIn().setBody(list);
		
	}

}
