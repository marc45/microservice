package org.apache.micro.common.exceptioin;

public abstract class ExceptionUtil {

	public static void throwIllegalArgumentException(boolean expression,String code){
		if(!expression){
			throw new IllegalArgumentException(code) ;
		}
	}
}
