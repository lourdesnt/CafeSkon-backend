package com.example.cafeskon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.Cart;
import com.example.cafeskon.repository.CafeUserRepository;
import com.example.cafeskon.repository.CartRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CafeUserRepository userRepository;
	
	@GetMapping("/")
	public ResponseEntity<Cart> getCart(@RequestBody String username){
		Optional<Cart> cart = cartRepository.findByCafeUser(userRepository.findById(username).get());
		if(!cart.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(cart.get(), HttpStatus.OK);
		}
	}
	
}
