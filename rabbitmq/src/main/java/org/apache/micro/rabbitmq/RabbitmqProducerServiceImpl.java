package org.apache.micro.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqProducerServiceImpl implements RabbitmqProducerService{
	
	@Autowired
	private RabbitTemplate rabbitTemplate ;
	
	@Scheduled(fixedDelay= 60*1000l)
	public void test(){

		for(int i=0 ;i< 1000 ;i++){
			
			rabbitTemplate.convertSendAndReceive("foo","hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohello");

			Object obj = rabbitTemplate.receiveAndConvert(null, 1000l) ;
			
			
		}
	}

}
