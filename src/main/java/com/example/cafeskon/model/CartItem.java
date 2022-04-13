package com.example.cafeskon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CafeUser customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="cart_id", referencedColumnName="id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cart cart;

	public CartItem(int quantity, double price, Product product, CafeUser customer, Cart cart) {
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.customer = customer;
		this.cart = cart;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CafeUser getCustomer() {
		return customer;
	}

	public void setCustomer(CafeUser customer) {
		this.customer = customer;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
