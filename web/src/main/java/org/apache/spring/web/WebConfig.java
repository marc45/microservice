package org.apache.spring.web;

import java.util.List;

import org.apache.spring.web.interceptor.ExceptionResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import(value={})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	
	@Bean(name="messageSource")
	public MessageSource getMessageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource() ;
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource ;
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		HandlerExceptionResolver resolver = new ExceptionResolver() ;
		exceptionResolvers.add(resolver) ;
	}
	

	
}
