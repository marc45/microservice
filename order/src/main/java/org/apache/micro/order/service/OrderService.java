package org.apache.micro.order.service;

import org.apache.micro.order.domain.OrderRequest;
import org.apache.micro.order.domain.OrderResponse;

public interface OrderService {

	public OrderResponse receive(OrderRequest request) ;
}
