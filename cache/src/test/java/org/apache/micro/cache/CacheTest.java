package org.apache.micro.cache;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.micro.cache.service.impl.IdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=CacheApplication.class)
public class CacheTest{
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate ;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	@Autowired
	private IdService idService ;
	
	private Queue<String[]> data = new ConcurrentLinkedQueue<>() ;
	
	private CountDownLatch pcountDown = new CountDownLatch(1) ;
	
	@Test
	public void testInitRedis() throws JsonProcessingException, InterruptedException{

		Thread p = new Thread(new P()) ;
		p.start();
		
		while(pcountDown.getCount() != 0){
			Thread.sleep(3000l);
		}
		
		long cstart = System.currentTimeMillis() ;
		int pool = 20 ;
		ExecutorService cservice = Executors.newFixedThreadPool(pool) ;
		for(int i = 0 ;i < pool ; i++){
			cservice.execute(new C());
		}
		cservice.shutdown();
		
		while(!cservice.isTerminated()){
			Thread.sleep(3000l);
		}
		System.out.println("c cost:"+(System.currentTimeMillis()-cstart));
	}
	
	class P implements Runnable{

		@Override
		public void run() {
			try{
				long start = System.currentTimeMillis() ;
				for(int i = 0 ;i < 1000000 ;i++){
					TestDomain domain = new TestDomain() ;
					domain.setIdno("350525198611014972"+i);
					domain.setName("黄跃文");
					domain.setAddress("福建省泉州市永春县石鼓镇半岭村3组810号"+i);
					domain.setBirth(new Date());
					domain.setId(idService.nextId());
					data.add(new String[]{String.valueOf(domain.getId()),objectMapper.writeValueAsString(domain)}) ;
				}
				pcountDown.countDown();
				System.out.println("producer cost:"+(System.currentTimeMillis()-start));
			}catch(Throwable t){
				t.printStackTrace();
			}
		}
	}
	
	
	class C implements Runnable{

		@Override
		public void run() {
			String[] test = null ;
			try{
				while((test = data.poll()) != null ){
					if(test == null || test.length <=0){
						continue ;
					}
					stringRedisTemplate.opsForValue().set(test[0], test[1]);
					test = null ;
				}
			}catch(Throwable t){
				t.printStackTrace();
				System.out.println(test[0]+" "+test[1]);
			}
			
			
		}
		
	}
	
}
