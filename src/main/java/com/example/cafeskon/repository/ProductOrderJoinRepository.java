package com.example.cafeskon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeskon.model.ProductOrderJoin;
import com.example.cafeskon.model.ProductOrderJoinId;

public interface ProductOrderJoinRepository extends JpaRepository<ProductOrderJoin, ProductOrderJoinId>{

}
