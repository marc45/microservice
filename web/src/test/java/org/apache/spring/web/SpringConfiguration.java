package org.apache.spring.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages={"org.apache.spring"})
public class SpringConfiguration {

	@Bean(name="messageSource")
	public MessageSource getMessageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource() ;
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource ;
	}
}
