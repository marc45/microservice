package org.apache.spring.web.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

@Component
public class BizAssert implements MessageSourceAware{
	
	@Autowired
	private static MessageSource messageSource ;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

	public static void throwIlleg(boolean expression,String messageCode,Object ... args){
		if(!expression){
			String message = messageSource.getMessage(messageCode, args, Locale.getDefault()) ;  
			throw new IllegalArgumentException(message) ;
		}
	}


	
}
