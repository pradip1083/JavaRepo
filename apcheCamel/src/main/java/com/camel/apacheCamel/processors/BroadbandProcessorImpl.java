package com.camel.apacheCamel.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BroadbandProcessorImpl implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Map<String,String>map=new HashMap<>();
		map=(Map)exchange.getIn().getBody(Map.class);
		
		System.out.println(map);
		
		exchange.setProperty("map", map);
		
		
	}

}
