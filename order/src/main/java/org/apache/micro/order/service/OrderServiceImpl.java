package org.apache.micro.order.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.micro.order.domain.order.OrderRequest;
import org.apache.micro.order.domain.order.OrderResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ComponentScan
@RestController
@RequestMapping(path="/order")
public class OrderServiceImpl implements OrderService{


	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class) ;



//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Autowired
//	private LoadBalancerClient loadBalancer ;
	
	private RabbitTemplate rabbitTemplate ;
	

	
	@Autowired
	private ProductService productClient ;

	@Override
	@RequestMapping("/receive")
	@HystrixCommand(fallbackMethod = "receiveFailed")
	public OrderResponse receive(OrderRequest request) {

		if(LOGGER.isDebugEnabled()){
//			LOGGER.debug("xxx{}{}",new Object[]{arg1,arg2});
		}

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
