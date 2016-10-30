package org.apache.micro.order.domain;

public class MailAddress extends BaseDomain {

	private int Id ;
	
	private String address ;
	
	private String phone ;
	
	private String recMan ;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRecMan() {
		return recMan;
	}

	public void setRecMan(String recMan) {
		this.recMan = recMan;
	}
	
	
}
