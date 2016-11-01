package org.apache.micro.cache.service;

import org.apache.micro.cache.domain.DataSynDomain;
import org.apache.micro.cache.domain.StringObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSynConsumer implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSynConsumer.class)  ;
	
	private StringRedisTemplate stringRedisTemplate ;
	
	private ObjectMapper objectMapper ;

	@Override
	public void run() {
		try{
			StringObject sobject = null ;
			while(DataSynDomain.getInstance().getPlatch().getCount() != 0){
				sobject = DataSynDomain.getInstance().pollStringObject() ;
				if(sobject != null){
					stringRedisTemplate.opsForValue().set(sobject.getKey(), objectMapper.writeValueAsString(sobject.getData()));
				}
				sobject = null ;
			}
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}

}
