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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo Order correspondiente a pedido
 * 
 * @author Lourdes Navarro
 *
 */
@Entity
@Table(name="orders")
public class Order {
	
	/**
	 * Atributo correspondiente al ID del pedido (tipo Integer)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * Atributo correspondiente al usuario que realiza el pedido (tipo CafeUser)
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CafeUser customer;
	
	/**
	 * Atributo correspondiente al nombre de la persona que realiza el pedido (tipo String)
	 */
	private String firstName;

	/**
	 * Atributo correspondiente al apellido de la persona que realiza el pedido (tipo String)
	 */
	private String lastName;
	
	/**
	 * Atributo correspondiente a la direccion de la vivienda de la persona que realiza el pedido (tipo String)
	 */
	private String address;
	
	/**
	 * Atributo correspondiente al codigo postal de la vivienda de la persona que realiza el pedido (tipo String)
	 */
	@Size(min = 5)
	private String postalCode;
	
	/**
	 * Atributo correspondiente al telefono de la persona que realiza el pedido (tipo String)
	 */
	@Size(max = 9)
	private String phone;
	
	/**
	 * Atributo correspondiente al metodo de pago con el que se va a pagar el pedido (tipo String)
	 */
	private String payment;
	
	/**
	 * Atributo correspondiente a la fecha en la que se realiza el pedido (tipo Date)
	 */
	@Column(name = "order_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;

	/**
	 * Constructor predeterminado
	 */
	public Order() {
		
	}
	
	/**
	 * Constructor parametrizado
	 * @param orderDto Modelo DTO del pedido
	 */
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

	public CafeUser getCustomer() {
		return customer;
	}

	public void setCustomer(CafeUser customer) {
		this.customer = customer;
	}
	
	
	
}
