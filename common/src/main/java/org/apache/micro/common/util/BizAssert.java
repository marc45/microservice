package org.apache.micro.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;

public class BizAssert implements MessageSourceAware{
	
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
