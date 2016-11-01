package org.apache.micro.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

	@RabbitListener()
	public void consumer(String content){
		
	}
}
