package org.apache.micro.order.domain.order;

import org.apache.micro.order.domain.order.Order;

import java.util.List;

public class OrderResponse {

	private long id ;

	private String status ;
	
	private String message ;

	private List<Order> orders;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
