package org.apache.micro.cache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/syn")
public class DataSynController {

	
	public void init(){
		
	}
	
	@RequestMapping(path="/{table}")
	public void init(String table) {
		
	}
}
