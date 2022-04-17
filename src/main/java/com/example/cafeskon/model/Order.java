package com.example.cafeskon.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Cart cart;
	
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	private String address;
	
	@NotBlank
	@Size(min = 5)
	private String postalCode;
	
	@NotBlank
	@Size(max = 9)
	private String phone;
	
	@NotBlank
	private String payment;
	
	@Column(name = "order_date")
	private Date orderDate;

	public Order() {
		
	}

	public Order(Cart cart, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String address,
			@NotBlank @Size(min = 5) String postalCode, @NotBlank @Size(max = 9) String phone, @NotBlank String payment,
			Date orderDate) {
		super();
		this.cart = cart;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phone = phone;
		this.payment = payment;
		this.orderDate = orderDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
	
	
	
}
