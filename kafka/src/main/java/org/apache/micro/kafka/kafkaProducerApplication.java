package org.apache.micro.kafka;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafkaProducerApplication {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.1.109:9092,192.168.1.110:9092,192.168.1.111:9092");
		props.put("acks", "1");
//		props.put("retries", 3);
//		props.put("batch.size", 10);
//		props.put("linger.ms", 1);
//		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		for(int j = 0 ;j < 100 ;j++){
			producer.send(new ProducerRecord<String, String>("test3", UUID.randomUUID().toString(),getRandomString(100))) ;
		}
		producer.flush();
		producer.close();

//		int pool = 1 ;
//		ExecutorService executorService = Executors.newFixedThreadPool(pool) ;
//		CountDownLatch countDownLatch = new CountDownLatch(pool) ;
//		for(int i = 0 ;i < pool ;i++){
//			executorService.submit(()->{
//				Producer<String, String> producer = new KafkaProducer<>(props);
//				for(int j = 0 ;j < 10 ;j++){
//					producer.send(new ProducerRecord<String, String>("test1", UUID.randomUUID().toString(),getRandomString(100))) ;
//				}
//				countDownLatch.countDown();
//				producer.flush();
//				producer.close();
//			}) ;
//		}
//
//		while(countDownLatch.getCount() != 0){
//			try {
//				Thread.sleep( 500l);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		executorService.shutdown();
	}

	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}


}
