package com.example.cafeskon.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductOrderJoinId implements Serializable {
	
	private Integer order;
	
	private Integer product;

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
