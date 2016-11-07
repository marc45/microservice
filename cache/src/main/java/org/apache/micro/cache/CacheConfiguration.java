package org.apache.micro.cache;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.micro.cache.service.impl.IdService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages="org.apache.micro.cache.mapper")
@PropertySource("classpath:jdbc.properties")
public class CacheConfiguration {
	
	@Autowired
	private Environment env ;
	
	
	@Bean(destroyMethod="close")
	public DataSource getDataSource(){
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.hikari.max-pool-size")));
		return dataSource ;
	}
	
	@Bean
	public IdService IdService(){
		IdService idService = new IdService(0, 0) ;
		return idService ;
	}
	
//	@Bean
//	public JedisConnectionFactory getJedisConnectionFactory(){
//		
//		JedisPoolConfig config = new JedisPoolConfig() ;
//		config.setMaxIdle(Integer.parseInt(env.getProperty("redis.maxIdle")));
//		config.setMaxTotal(Integer.parseInt(env.getProperty("redis.maxTotal")));
//		config.setMinIdle(Integer.parseInt(env.getProperty("redis.minIdle")));
//		config.setMaxWaitMillis(Integer.parseInt(env.getProperty("redis.maxWaitMillis")));
//		
//		JedisConnectionFactory jedisFactory = new JedisConnectionFactory(config) ;
//		return jedisFactory ;
//	}
//	
//	@Bean
//	public StringRedisTemplate getStringRedisTemplate(){
//		StringRedisTemplate template = new StringRedisTemplate();
//		template.setConnectionFactory(getJedisConnectionFactory());
//		return template ;
//	}
	
//	@Bean
//	public MapperScannerConfigurer getMapperScannerConfigurer(){
//		MapperScannerConfigurer scanner = new MapperScannerConfigurer();
//		scanner.setBasePackage("org.apache.micro.cache.mapper");
//		return scanner ;
//	}
	
	@Bean
	public SqlSessionFactory getSqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean() ;
		sqlSessionFactoryBean.setDataSource(getDataSource());
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject() ; 
		return sqlSessionFactory ;
	}
}
