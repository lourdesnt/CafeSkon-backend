package com.example.cafeskon.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CafeUser customer;
	
//	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
//	private List<ProductOrderJoin> productOrder;
	
	private String firstName;

	private String lastName;
	
	private String address;
	
	@Size(min = 5)
	private String postalCode;
	
	@Size(max = 9)
	private String phone;
	
	private String payment;
	
	@Column(name = "order_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;

	public Order() {
		
	}
	
	public Order(OrderDto orderDto) {
		this.id = orderDto.getId();
		this.firstName = orderDto.getFirstName();
		this.lastName = orderDto.getLastName();
		this.address = orderDto.getAddress();
		this.postalCode = orderDto.getPostalCode();
		this.phone = orderDto.getPhone();
		this.payment = orderDto.getPayment();
		this.orderDate = orderDto.getOrderDate();
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

	public CafeUser getCustomer() {
		return customer;
	}

	public void setCustomer(CafeUser customer) {
		this.customer = customer;
	}
	
	
	
}
