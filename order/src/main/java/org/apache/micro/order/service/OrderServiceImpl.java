package org.apache.micro.order.service;

import org.apache.micro.order.domain.OrderRequest;
import org.apache.micro.order.domain.OrderResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@ComponentScan
@RestController
@RequestMapping(path="/order")
public class OrderServiceImpl implements OrderService{
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Autowired
//	private LoadBalancerClient loadBalancer ;
	
	private RabbitTemplate rabbitTemplate ;
	

	
	@Autowired
	private ProductClient productClient ;

	@Override
	@RequestMapping("/receive")
	@HystrixCommand(fallbackMethod = "receiveFailed")
	public OrderResponse receive(OrderRequest request) {
		
		//获取商品信息
		//并发处理库存扣减，及财务捐款
		
		
		
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
