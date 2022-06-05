package com.example.cafeskon.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase para establecer un ID general que representa la relacion Product-Order
 * 
 * @author Lourdes Navarro
 *
 */
public class ProductOrderJoinId implements Serializable {
	
	/**
	 * Atributo correspondiente al ID del pedido (tipo Integer)
	 */
	private Integer order;
	
	/**
	 * Atributo correspondiente al ID del producto (tipo Integer)
	 */
	private Integer product;

	//GETTERS Y SETTERS
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	//HASHCODE Y EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrderJoinId other = (ProductOrderJoinId) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	

}
