package org.apache.micro.rabbitmq;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * RabbitmqApplication
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class RabbitmqApplication {
	
	@Autowired
	private CachingConnectionFactory connectionFactory ;
	
	@Autowired
	private RabbitTemplate rabbitTemplate ;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	@Bean
	public SimpleMessageListenerContainer getSimpleMessageListenerContainer(){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory) ;
		container.setConcurrentConsumers(100);
		container.setQueueNames("foo");
		container.setAutoStartup(true);
//		container.setChannelTransacted(true);
//		container.setPrefetchCount(1);
		FixedBackOffPolicy policy = new FixedBackOffPolicy() ;
//		container.setRecoveryBackOff(new recover);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		//手工确认，确认超时
		container.setMessageListener(new ChannelAwareMessageListener(){
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				TestDomain domain = objectMapper.readValue(message.getBody(),TestDomain.class) ;
				Thread.sleep(30l);
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		});
//		container.setMessageListener(new MessageListener() {
//
//			@Override
//			public void onMessage(Message arg0) {
//
//			System.out.println("now1 "+System.currentTimeMillis()) ;
//				try {
//					TestDomain domain = objectMapper.readValue(arg0.getBody(),TestDomain.class) ;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				System.out.println("now2 "+System.currentTimeMillis()) ;
//
//			}
//		});
		return container ;
	}
	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RabbitmqApplication.class, args) ;
		
	}
	
	
	public void test() throws Exception{

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.20.170");
//		template.
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setChannelCacheSize(10);
		connectionFactory.setPublisherConfirms(true);
		RabbitTemplate template = new RabbitTemplate(connectionFactory) ;
//		template.re
		Message message = template.receive("hello") ;
		System.out.println(new String(message.getBody()));
		
//		template.convertAndSend("hello", new String("hello world"));
//		template.convertAndSend("world", new String("hello world"));
		
//		template.setRetryTemplate(new RetryTemplate());
	
	}
}
