package org.apache.micro.product.domain;

public class ProductResponse {

	private String status;

	private String message;
	
	private Object data ;

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
