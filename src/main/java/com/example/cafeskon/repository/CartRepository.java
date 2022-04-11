package com.example.cafeskon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.CafeUser;
import com.example.cafeskon.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	public Optional<Cart> findByCafeUser(CafeUser cafeUser);
}
