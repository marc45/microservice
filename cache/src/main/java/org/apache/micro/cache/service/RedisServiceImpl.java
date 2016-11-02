package org.apache.micro.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate ;
	
	@Autowired
	private ObjectMapper objectMapper ;
	
	
	public <T> void set(String key,T value){
		try {
			String json = objectMapper.writeValueAsString(value) ;
			stringRedisTemplate.opsForValue().set(key, json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e) ;
		}
	}
	
}
