package org.apache.micro.product.service;

import org.apache.micro.product.domain.ProductRequest;
import org.apache.micro.product.domain.ProductResponse;

public interface ProductService {

	public ProductResponse save(ProductRequest requesst) ;
	
	public Integer add(Integer a,Integer b) ;
}
