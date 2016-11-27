package org.apache.micro.good;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class ProductApplication {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean() ;
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean ;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer configurer = new MapperScannerConfigurer() ;
		configurer.setBasePackage("org.apache.micro.good.mapper");
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		return configurer ;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args) ;
	}
}
