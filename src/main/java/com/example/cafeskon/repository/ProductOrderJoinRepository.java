package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.Order;
import com.example.cafeskon.model.ProductOrderJoin;
import com.example.cafeskon.model.ProductOrderJoinId;

@Repository
public interface ProductOrderJoinRepository extends JpaRepository<ProductOrderJoin, ProductOrderJoinId>{
	List<ProductOrderJoin> findByOrder(Order order);

}
