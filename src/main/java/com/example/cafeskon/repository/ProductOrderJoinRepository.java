package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.Order;
import com.example.cafeskon.model.ProductOrderJoin;
import com.example.cafeskon.model.ProductOrderJoinId;

/**
 * Repositorio para la relacion Product-Order
 * 
 * @author Lourdes Navarro
 *
 */
@Repository
public interface ProductOrderJoinRepository extends JpaRepository<ProductOrderJoin, ProductOrderJoinId>{
	
	/**
	 * Método para encontrar por pedido
	 * @param order Pedido
	 * @return productOrderJoin
	 */
	List<ProductOrderJoin> findByOrder(Order order);

}
