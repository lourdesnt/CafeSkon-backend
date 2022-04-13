package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.CartItem;
import com.example.cafeskon.model.CafeUser;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	public List<CartItem> findByCustomer (CafeUser cafeUser);
	
}
