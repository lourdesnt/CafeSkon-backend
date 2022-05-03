package com.example.cafeskon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.repository.ProductOrderJoinRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) //Es donde se va a abrir nuestro front
@RestController
@RequestMapping("/product-order")
public class ProductOrderJoinController {

	@Autowired
	private ProductOrderJoinRepository prodOrderRepository;
	
}
