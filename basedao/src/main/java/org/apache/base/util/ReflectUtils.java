package org.apache.base.util;

import com.google.common.base.Preconditions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ReflectUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);
	
	public static Object[] invokeGetId(Object target, Class<?> clz){
		Object[] result = null ;

		Field[] fields = clz.getFields() ;
		for (Field field : fields) {
			Id id = (Id) field.getAnnotation(Id.class);
			if(id != null){
				result[0] = field.getName() ;
				result[1] = invokeGet(target, clz, field.getName()) ;
				break ;
			}
		}
		
		return result ;
	}

	public static Object invokeGet(Object target, Class<?> clz, String field) {
		String getter = toGetter(field);
		Method method = toGetterMethod(clz, getter);
		Object result = null;
		try {
			result = method.invoke(target, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	private static String toGetter(String field) {
		String getMethodName = "get" + field.replace(field.charAt(0), (char) (field.charAt(0) - ' '));
		return getMethodName;
	}

	private static Method toGetterMethod(Class<?> clz, String getter) {
		Method getterMethod = null;
		try {
			getterMethod = clz.getMethod(getter, new Class[0]);
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.error(e.getMessage());
		}
		Preconditions.checkNotNull(getterMethod);
		return getterMethod;
	}
}
