package org.apache.micro.product.service;

import org.apache.micro.product.domain.ProductRequest;
import org.apache.micro.product.domain.ProductResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
@RequestMapping(path="/product")
public class ProductServiceImpl implements ProductService {

	@Override
	@RequestMapping(path="/save")
	public ProductResponse save(ProductRequest requesst) {
		
		System.out.println("product save..............");
		
		ProductResponse resp = new ProductResponse() ;
		resp.setStatus("200");
		resp.setMessage("is ok");
		return resp;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, path = "/add")
	public Integer add(@RequestParam("a")Integer a, @RequestParam("b")Integer b) {
		//应该判断是否有溢出
		Integer result = a+b ;
		System.out.println(result);
		return result ;
	}

	
}
