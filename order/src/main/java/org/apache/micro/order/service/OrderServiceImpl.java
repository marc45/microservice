package org.apache.micro.order.service;

import java.util.List;

import org.apache.micro.order.domain.OrderRequest;
import org.apache.micro.order.domain.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@ComponentScan
@RestController
@RequestMapping(path="/orderr")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	@RequestMapping("/receive")
	@HystrixCommand(fallbackMethod = "receiveFailed")
	public OrderResponse receive(OrderRequest request) {
		System.out.println("order receive........");
		
		List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCTAPPLICATION") ;
		System.out.println("product instances size => "+instances.size());
		
		
		
		OrderResponse resp = new OrderResponse() ;
		resp.setStatus("200");
		resp.setMessage("is ok");
		return resp;
	}
	
	public OrderResponse receiveFailed(OrderRequest request){
		
		System.out.println("receive failed fast.............");
		
		OrderResponse resp = new OrderResponse() ;
		resp.setStatus("200");
		resp.setMessage("failed fast");
		return resp ;
	}

}
