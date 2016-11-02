package org.apache.micro.rabbitmq;

import java.io.IOException;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;



/**
 * RabbitmqApplication
 *
 */
public class RabbitmqApplication {
	
	
	public static void main(String[] args) throws IOException {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.1.110");
//		template.
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		

		Connection connection = connectionFactory.createConnection();
		Channel channel = connection.createChannel(true) ;
//		channel.basiccon
//		channel.pu
		
	}
}
