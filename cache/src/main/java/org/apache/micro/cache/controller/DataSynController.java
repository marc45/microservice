package org.apache.micro.cache.controller;

import org.apache.micro.cache.service.DataSynService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/syn")
public class DataSynController {
	
	private DataSynService dataSynService ;

	
	public void init(){
		dataSynService.init(null) ;
	}
	
	@RequestMapping(path="/{table}")
	public void init(String table) {
		dataSynService.init(table) ;
	}
}
