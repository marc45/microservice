package org.apache.spring.web.util;

import org.slf4j.Logger;

public abstract class LogUtil {

	public static void debug(Logger logger, String format, Object... args) {
		if (logger.isDebugEnabled()) {
			logger.debug(format, args);
		}
	}

	public static void info(Logger logger, String format, Object... args) {
		if (logger.isInfoEnabled()) {
			logger.info(format, args);
		}
	}

	public static void warn(Logger logger, String format, Object... args) {
		if (logger.isWarnEnabled()) {
			logger.warn(format, args);
		}
	}

	public static void error(Logger logger, String format, Object... args) {
		if (logger.isErrorEnabled()) {
			logger.error(format, args);
		}
	}
	
	public static void error(Logger logger, Throwable t) {
		if (logger.isErrorEnabled()) {
			logger.error(t.getMessage(),t);
		}
	}

}
