package org.apache.micro.order.service;

import org.apache.micro.order.domain.OrderRequest;
import org.apache.micro.order.domain.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@ComponentScan
@RestController
@RequestMapping(path="/orderr")
public class OrderServiceImpl implements OrderService{
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Autowired
//	private LoadBalancerClient loadBalancer ;
	
	@Autowired
	private ProductClient productClient ;

	@Override
	@RequestMapping("/receive")
	@HystrixCommand(fallbackMethod = "receiveFailed")
	public OrderResponse receive(OrderRequest request) {
		System.out.println("order receive........");
		
//		String result = productClient.add(10,20) ;
//		System.out.println(result);
//		
//		result = productClient.save() ;
//		System.out.println(result);
		
		if(Math.random() > 0.5){
			throw new RuntimeException("is not ok") ;
		}
//		ServiceInstance instance = loadBalancer.choose("PRODUCTAPPLICATION") ;
//		System.out.println(instance.getMetadata().toString()) ;
////		List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCTAPPLICATION") ;
//		System.out.println(instance.getUri().toString());
		
		
		
		OrderResponse resp = new OrderResponse() ;
		resp.setStatus("200");
		resp.setMessage("is ok");
		return resp;
	}
	
	public OrderResponse receiveFailed(OrderRequest request,Throwable t){
		t.printStackTrace();
		
		System.out.println("receive failed fast.............");
		
		OrderResponse resp = new OrderResponse() ;
		resp.setStatus("200");
		resp.setMessage("failed fast");
		return resp ;
	}

}
