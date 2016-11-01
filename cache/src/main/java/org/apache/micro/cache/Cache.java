package org.apache.micro.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class Cache {
	
	@Autowired
	private Environment env ;
	
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory(){
		
		JedisPoolConfig config = new JedisPoolConfig() ;
		config.setMaxIdle(Integer.parseInt(env.getProperty("redis.maxIdle")));
		config.setMaxTotal(Integer.parseInt(env.getProperty("redis.maxTotal")));
		config.setMinIdle(Integer.parseInt(env.getProperty("redis.minIdle")));
		config.setMaxWaitMillis(Integer.parseInt(env.getProperty("redis.maxWaitMillis")));
		
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory(config) ;
		return jedisFactory ;
	}
	
	@Bean
	public StringRedisTemplate getStringRedisTemplate(){
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(getJedisConnectionFactory());
		return template ;
	}
	
}
