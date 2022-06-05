package com.example.cafeskon.model;

import java.sql.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Modelo DTO de pedido que estara incluido en lista de pedidos
 * 
 * @author Lourdes Navarro
 *
 */
public class OrderListDto {
	
	/**
	 * Atributo correspondiente al ID del pedido (tipo Integer)
	 */
	private Integer id;
	
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
	 * Atributo correspondiente al map de productos por nombre y cantidad
	 */
	private Map<String, Integer> productMap;

	/**
	 * Constructor parametrizado
	 * @param order Pedido
	 * @param productMap Map de productos por nombre y cantidad
	 */
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

	//GETTERS Y SETTERS
	
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
