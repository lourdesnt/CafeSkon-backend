package com.example.cafeskon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.Order;

/**
 * Repositorio para pedidos (Order)
 * 
 * @author Lourdes Navarro
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
