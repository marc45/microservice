package org.apache.micro.message;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

	@Bean
	public SqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource) {

		SqlSessionFactoryBean factory = new SqlSessionFactoryBean() ;
		factory.setDataSource(dataSource);
		return factory;
	}
	
	@Bean
	public MapperScannerConfigurer getMapperScannerConfigurer(){
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("");
		return configurer ;
	}
}
