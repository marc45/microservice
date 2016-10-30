package org.apache.spring.web;

import java.util.Locale;

import org.apache.spring.web.util.BizAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;


public class Spring {
	
	private AnnotationConfigApplicationContext cxt ;
	
	private MessageSource messageSource ;
	

	@Before
	public void setUp() throws Exception {
		cxt = new AnnotationConfigApplicationContext(SpringConfiguration.class) ;
		messageSource = cxt.getBean(ResourceBundleMessageSource.class) ;
	}

	@Test
	public void test() {
		Assert.assertNotNull(messageSource);
		BizAssert.throwIlleg(true, "", null);
	}

}
