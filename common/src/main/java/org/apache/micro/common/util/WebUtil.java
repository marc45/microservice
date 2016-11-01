package org.apache.micro.common.util;

import javax.servlet.http.HttpServletRequest;

public abstract class WebUtil {

	public static boolean isAajax(HttpServletRequest req){
		String strAjax = req.getHeader("X-Requested-With") ;
		boolean isAjax = (strAjax != null || "XMLHttpRequest".equals(strAjax));
		return isAjax ;
	}
}
