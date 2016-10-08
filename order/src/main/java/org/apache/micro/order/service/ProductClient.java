package org.apache.micro.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCTAPPLICATION")
public interface ProductClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/product/add")
	public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b) ;
	@RequestMapping(method = RequestMethod.GET, value = "/product/save")
	public String save() ;

}
