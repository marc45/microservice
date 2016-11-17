package org.apache.micro.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("API-GATEWAY")
public interface ProductService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/productapplication/product/add")
	public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b) ;
	
	@RequestMapping(method = RequestMethod.GET, value = "/productapplication/product/save")
	public String save() ;

}
