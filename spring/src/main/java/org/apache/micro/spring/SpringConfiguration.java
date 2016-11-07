package org.apache.micro.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {

	@Autowired
	private Environment env ;
	
	@Bean
	public String getString(){
		String result = env.getProperty("jdbc.driver") ;
		System.out.println(result);
		return result ;
	}
}
