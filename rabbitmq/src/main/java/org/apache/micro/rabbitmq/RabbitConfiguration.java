package org.apache.micro.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class RabbitConfiguration {
	
	@Autowired
	private Environment env ;

	@Bean
	public ConnectionFactory getConnectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory(null, 15672) ;
		factory.setUsername(env.getProperty("rabbit.username"));
		factory.setPassword(env.getProperty("rabbit.password"));
		factory.setPublisherConfirms(true);
		return factory ;
	}
	
	
}
