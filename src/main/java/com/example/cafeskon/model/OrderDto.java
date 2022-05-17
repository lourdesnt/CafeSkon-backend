package com.example.cafeskon.model;

import java.sql.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;


public class OrderDto {

	private Integer id;
	
	private String customerId;
	
	private String firstName;

	private String lastName;
	
	private String address;
	
	private String postalCode;
	
	private String phone;
	
	private String payment;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	
	private HashMap<Integer, Integer> productMap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public HashMap<Integer, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(HashMap<Integer, Integer> productMap) {
		this.productMap = productMap;
	}
	
	
}
