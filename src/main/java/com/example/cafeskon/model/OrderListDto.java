package com.example.cafeskon.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderListDto {
	
	private Integer id;
	
	private String firstName;

	private String lastName;
	
	private String address;
	
	private String postalCode;
	
	private String phone;
	
	private String payment;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	
	private Map<String, Integer> productMap;

	public OrderListDto(Order order, Map<String, Integer> productMap) {
		this.id = order.getId();
		this.firstName = order.getFirstName();
		this.lastName = order.getLastName();
		this.address = order.getAddress();
		this.postalCode = order.getPostalCode();
		this.phone = order.getPhone();
		this.payment = order.getPayment();
		this.orderDate = order.getOrderDate();
		this.productMap = productMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Map<String, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<String, Integer> productMap) {
		this.productMap = productMap;
	}
	
	

}
