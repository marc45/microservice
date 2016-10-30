package org.apache.spring.web;

import org.apache.spring.web.util.BizAssert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


	@RequestMapping(path="/")
	public String login(){
		return "welcome";
	}
	
	@RequestMapping(path="/e")
	public String exception(){
		BizAssert.throwIlleg(false, "message", "bbbb");
		return "forward:/error" ;
	}
}
