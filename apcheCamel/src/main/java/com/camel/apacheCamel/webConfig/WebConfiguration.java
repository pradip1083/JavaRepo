
package com.camel.apacheCamel.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		

		//registry.addResourceHandler("/**").addResourceLocations("/static/");
		registry.addResourceHandler("/**/*.css").addResourceLocations("/static/css/");
	}
}
