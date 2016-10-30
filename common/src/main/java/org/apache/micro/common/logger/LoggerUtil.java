package org.apache.micro.common.logger;

import org.slf4j.Logger;

/**
 * 日志工具类
 * @author murphy
 *
 */
public abstract class LoggerUtil {
	
	public static void debug(Logger logger,String format,Object ... args){
		if(logger.isDebugEnabled()){
			logger.debug(format, args);
		}
	}
	
	
	public static void info(Logger logger,String format,Object ... args){
		if(logger.isInfoEnabled()){
			logger.info(format, args);
		}
	}
	
	public static void warn(Logger logger,String format,Object ... args){
		if(logger.isWarnEnabled()){
			logger.warn(format, args);
		}
	}
	
	public static void error(Logger logger,String format,Object ... args){
		if(logger.isErrorEnabled()){
			logger.error(format, args);
		}
	}
	
}
