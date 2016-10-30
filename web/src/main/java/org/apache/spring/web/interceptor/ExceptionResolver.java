package org.apache.spring.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.spring.web.util.LogUtil;
import org.apache.spring.web.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class) ; 


	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object obj,
			Exception ex) {
		LogUtil.error(LOGGER, ex);
		
		if(WebUtil.isAajax(req)){
			
		}
		
		ModelAndView view = new ModelAndView("welcome") ;
		Map<String,Object> model = view.getModel() ;
		model.put("message", ex.getMessage()) ;
		return view;
	}
	
	

}
