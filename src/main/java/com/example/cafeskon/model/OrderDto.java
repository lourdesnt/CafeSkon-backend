package com.example.cafeskon.model;

import java.sql.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Modelo DTO de pedido
 * 
 * @author Lourdes Navarro
 *
 */
public class OrderDto {

	/**
	 * Atributo correspondiente al ID del pedido (tipo Integer)
	 */
	private Integer id;
	
	/**
	 * Atributo correspondiente al ID del cliente que realiza el pedido (tipo String)
	 */
	private String customerId;
	
	/**
	 * Atributo correspondiente al nombre del cliente que realiza el pedido (tipo String)
	 */
	private String firstName;

	/**
	 * Atributo correspondiente al apellido del cliente que realiza el pedido (tipo String)
	 */
	private String lastName;
	
	/**
	 * Atributo correspondiente a la direccion de la vivienda del cliente que realiza el pedido (tipo String)
	 */
	private String address;
	
	/**
	 * Atributo correspondiente al codigo postal de la vivienda del cliente que realiza el pedido (tipo String)
	 */
	private String postalCode;
	
	/**
	 * Atributo correspondiente al telefono del cliente que realiza el pedido (tipo String)
	 */
	private String phone;
	
	/**
	 * Atributo correspondiente al metodo de pago con el que se va a pagar el pedido (tipo String)
	 */
	private String payment;
	
	/**
	 * Atributo correspondiente a la fecha en la que se realiza el pedido (tipo Date)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	
	/**
	 * Atributo correspondiente al map de productos por ID y cantidad
	 */
	private HashMap<Integer, Integer> productMap;
	
	//GETTERS Y SETTERS

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
