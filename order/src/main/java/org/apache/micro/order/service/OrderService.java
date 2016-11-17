package org.apache.micro.order.service;

import org.apache.micro.order.domain.order.OrderRequest;
import org.apache.micro.order.domain.order.OrderResponse;

public interface OrderService {

	public OrderResponse save(OrderRequest request) ;

	public OrderResponse find(OrderRequest request) ;
}
