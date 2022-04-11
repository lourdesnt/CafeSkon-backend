package com.example.cafeskon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeskon.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
