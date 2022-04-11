package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeskon.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findAllByOrderByOrderDateDesc();
}
