package org.apache.micro.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Producer implements Runnable {

	private RabbitTemplate rabbitTemplate;

	public Producer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void run() {
		long start = System.currentTimeMillis() ;
		for(int i  =0 ;i< 10000 ;i++){
			rabbitTemplate.convertAndSend("foo", "hello worldhello worldhello worldhello worldhello worldhello worldhello worldhello world"+i);
		}
		
		System.out.println("cost "+(System.currentTimeMillis()-start));
	}

}
