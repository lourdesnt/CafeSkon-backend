package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeskon.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByNameEquals(String name);
	List<Product> findByCategory(Integer catergoryId);
}
