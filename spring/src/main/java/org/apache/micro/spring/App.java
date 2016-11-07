package org.apache.micro.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext cxt =  new AnnotationConfigApplicationContext(SpringConfiguration.class) ;
	}
}
