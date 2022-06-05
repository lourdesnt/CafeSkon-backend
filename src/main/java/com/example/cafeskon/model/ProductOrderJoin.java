package com.example.cafeskon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo ProductOrderJoin que representa la relacion Product-Order
 * 
 * @author Lourdes Navarro
 *
 */
@Entity
@Table(name="product_order")
@IdClass(ProductOrderJoinId.class)
public class ProductOrderJoin {
	
	/**
	 * Atributo correspondiente al pedido (tipo Order)
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName="id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Order order;
	
	/**
	 * Atributo correspondiente al producto (tipo Product)
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName="id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Product product;
	
	/**
	 * Atributo correspondiente a la cantidad del producto (tipo Integer)
	 */
	@Column(name = "quantity")
	private Integer quantity;
	
	/**
	 * Constructor predeterminado
	 */
	public ProductOrderJoin() {
		
	}
	
	//GETTERS Y SETTERS

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
