package org.apache.micro.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HuanagYueWen on 2016-11-28.
 */
public class KafkaConsumerApplication {

    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.109:9092,192.168.1.110:9092,192.168.1.111:9092");
        props.put("group.id", "test3");
        props.put("enable.auto.commit", "true");  //自动commit
        props.put("auto.commit.interval.ms", "1000"); //定时commit的周期
        props.put("session.timeout.ms", "30000"); //consumer活性超时时间
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        long start = System.currentTimeMillis() ;
        int pool = 10 ;
        ExecutorService executorService = Executors.newFixedThreadPool(pool) ;
        CountDownLatch countDownLatch = new CountDownLatch(pool) ;
        for(int i = 0 ;i < pool ;i++){
            executorService.submit(()->{
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList("test3")); //subscribe，foo，bar，两个topic
                int count   = 0 ;
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);

//                   count = count +records.count();
                    for (ConsumerRecord<String, String> record : records){
                        System.out.printf(Thread.currentThread().getName() +"offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
//                        System.out.println();
//                        try {
//                            Thread.sleep(20l);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
//                countDownLatch.countDown();
//                consumer.close();
            });



        }


        while(countDownLatch.getCount() != 0){
            try {
                Thread.sleep( 500l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("=================================cost "+(System.currentTimeMillis()-start));
        executorService.shutdown();

    }
}
