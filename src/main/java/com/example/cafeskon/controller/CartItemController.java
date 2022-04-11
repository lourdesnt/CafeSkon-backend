package com.example.cafeskon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.CartItem;
import com.example.cafeskon.repository.CafeUserRepository;
import com.example.cafeskon.repository.CartItemRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/cartitems")
public class CartItemController {
	
	@Autowired
	private CartItemRepository itemRepository;
	
	@Autowired
	private CafeUserRepository userRepository;
	
	@GetMapping("{username}/list")
	public ResponseEntity<List<CartItem>> getAllItemsByUser(@PathVariable String username){
		List<CartItem> items = new ArrayList<CartItem>();
		items = itemRepository.findByCafeUser(userRepository.findById(username).get());
		if(items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(items, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{username}/{id}")
	public ResponseEntity<CartItem> getItemById(@PathVariable("username") String username, @PathVariable("id") Integer id) {
		Optional<CartItem> itemData = itemRepository.findById(id);

		if (itemData.isPresent()) {
			return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{username}/new")
	public ResponseEntity<CartItem> createItem(@PathVariable("username") String username, @RequestBody CartItem newItem) {
		try {
			CartItem item = userRepository.findById(username).map(user -> {
			      newItem.setCustomer(user); return itemRepository.save(newItem);}).get();
			
			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartItem> updateItem(@PathVariable("id") Integer id, @RequestBody CartItem item) {
		Optional<CartItem> itemData = itemRepository.findById(id);
		
		if (itemData.isPresent()) {
			CartItem _item = itemData.get();
			_item.setQuantity(item.getQuantity());
			_item.setPrice(item.getPrice());
			return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Integer id) {
		try {
			itemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
