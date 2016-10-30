package org.apache.micro.rabbitmq.amqp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
	
	private final static String QUEUE_NAME = "foo";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.1.110");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
	    String message = "Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!";
	   
	    
	    long start = System.currentTimeMillis() ;
	    for(int i = 0 ;i< 10000 ;i++){
	    	channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());
	    }
	    System.out.println("cost "+(System.currentTimeMillis()-start));
	    System.out.println(" [x] Sent '" + message + "'");
	    channel.close();
	    connection.close();
	}
}
