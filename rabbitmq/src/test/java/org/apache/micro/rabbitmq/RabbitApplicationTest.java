package org.apache.micro.rabbitmq;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = { RabbitmqApplication.class })
public class RabbitApplicationTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	@Autowired
	private CachingConnectionFactory connectionFactory ;

	private String routeKey = "foo";

	private Exchange exchange;

	private AtomicInteger cack = new AtomicInteger(); 
	
	private AtomicInteger cnack = new AtomicInteger(); 
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSend() {



		rabbitTemplate.setConfirmCallback(new ConfirmCallback() {
			
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if(ack){
					cack.incrementAndGet() ;
				}else{
					cnack.incrementAndGet() ;
					System.out.println(cause);
				}
				
			}
		});
		
		rabbitTemplate.setReturnCallback(new ReturnCallback() {

			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				System.out.println("return back....");

			}
		});
		
//		rabbitTemplate.setReturnCallback(new );
		TestDomain domain = new TestDomain() ;
		domain.setIdno("350525198611014972");
		domain.setName("黄跃文");
		domain.setAddress("福建省泉州市永春县石鼓镇半岭村3组810号");

		try {
			String message = objectMapper.writeValueAsString(domain) ;
			long start = System.currentTimeMillis() ;
			int pool = 10 ;
			ExecutorService cservice = Executors.newFixedThreadPool(pool) ;
			for(int i = 0 ;i < pool ; i++){
				cservice.execute(new P(message));
			}
			cservice.shutdown();
			while(!cservice.isTerminated()){
			}

//			byte[] bdata = objectMapper.writeValueAsString(domain).getBytes() ;
//			for(int i =0 ;i< 10000 ;i++){
//				domain.setBirth(new Date());
//				MessageProperties prop = new MessageProperties() ;
//				prop.setTimestamp(new Date());
//				Message message = new Message(bdata,prop) ;
//				System.out.println("send1 "+System.currentTimeMillis());
//				rabbitTemplate.send("amq.direct","foo",message);
//				System.out.println("send2 "+System.currentTimeMillis());
//			}


			System.out.println("ack and nack "+cack.get()+" "+cnack.get());
			System.out.println("publisher cost:"+(System.currentTimeMillis()-start));
			
			Thread.sleep(6000l);
			
			System.out.println("ack and nack "+cack.get()+" "+cnack.get());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public class P implements Runnable{
		
		private String message ;
		
		private P(String message) {
			super();
			this.message = message;
		}

		@Override
		public void run() {
			byte[] bdata = message.getBytes() ;
			MessageProperties prop = new MessageProperties() ;
				Message msg = new Message(bdata,prop) ;
			for(int i =0 ;i< 10000 ;i++){
				rabbitTemplate.send("amq.direct",routeKey, msg);
//				rabbitTemplate.
			}
			
		}
		
	}

	@Test
	public void testReceive() {
//		Message message = rabbitTemplate.receive("hello") ;
//		System.out.println(new String(message.getBody()));
//		rabbitTemplate.setChannelTransacted(true);
//		Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false) ;
//		channel.
		rabbitTemplate.setConfirmCallback(new ConfirmCallback() {

			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				System.out.println("afasdfasdf "+ack+" "+cause);

			}
		});
		boolean receive = rabbitTemplate.receiveAndReply("hello",new ReceiveAndReplyCallback<String, Void>() {

			@Override
			public Void handle(String arg0) {
				System.out.println(arg0);

				return null;
			}
		});
	}

	@Test
	public void testAsynReceive(){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory) ;
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setConcurrentConsumers(1);
		container.setQueueNames("hello");
		container.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message arg0) {
				String message = new String(arg0.getBody(),Charset.forName("utf-8")) ;
				System.out.println(message);
			}
		});
		
		
		try {
			Thread.currentThread().sleep(3000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
