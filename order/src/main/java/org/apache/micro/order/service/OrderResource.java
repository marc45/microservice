package org.apache.micro.order.service;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.micro.order.domain.order.OrderRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;


@ComponentScan
@RestController
@RequestMapping(path="/order")
public class OrderResource{


	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderResource.class) ;

	@Autowired
	private GoodResource goodResource ;

//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Autowired
//	private LoadBalancerClient loadBalancer ;


	@RequestMapping("/receive")
	@HystrixCommand(fallbackMethod = "receiveFailed")
	public Response receive(OrderRequest request) {

		if(LOGGER.isDebugEnabled()){
//			LOGGER.debug("xxx{}{}",new Object[]{arg1,arg2});
		}

		//获取商品信息
		//并发处理库存扣减，及财务捐款


		Response response = Response.ok().build() ;

		return response ;
	}
	
	public Response receiveFailed(OrderRequest request,Throwable t){
		t.printStackTrace();
		
		System.out.println("receive failed fast.............");
		
		Response response = Response.ok(t).build() ;
		return response ;
	}




}
