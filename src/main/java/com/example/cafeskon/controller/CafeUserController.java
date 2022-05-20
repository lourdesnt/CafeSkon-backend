package com.example.cafeskon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.CafeUser;
import com.example.cafeskon.repository.CafeUserRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) //Es donde se va a abrir nuestro front
@RestController
@RequestMapping("/users")
public class CafeUserController {
	
	@Autowired
	private CafeUserRepository cafeUserRepository;
	
//	@Autowired
//	private RoleRepository roleRepository;
	
	@GetMapping("/login")
	public ResponseEntity<CafeUser> login(@RequestParam String username, @RequestParam String password) {
		Optional<CafeUser> user = cafeUserRepository.findById(username);
		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<CafeUser>> getAllUsers(){
		List<CafeUser> users = new ArrayList<CafeUser>();
		cafeUserRepository.findAll().forEach(users::add);
		if(users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<CafeUser> register(@Valid @RequestBody CafeUser user){
		Optional<CafeUser> userToRegister = cafeUserRepository.findById(user.getUsername());
		if (userToRegister.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			cafeUserRepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<CafeUser> getUserByUsername(@PathVariable("username") String username) {
		Optional<CafeUser> actualUser = cafeUserRepository.findById(username);
		if (actualUser.isPresent()) {
			return new ResponseEntity<>(actualUser.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/user/{username}")
	public ResponseEntity<CafeUser> updateUser(@PathVariable("username") String username, @RequestBody CafeUser user) {
		Optional<CafeUser> actualUser = cafeUserRepository.findById(username);

		if (actualUser.isPresent()) {
			CafeUser userToEdit = actualUser.get();
			userToEdit.setUsername(user.getUsername());
			userToEdit.setEmail(user.getEmail());
			userToEdit.setPassword(user.getPassword());
			return new ResponseEntity<>(cafeUserRepository.save(userToEdit), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String username) {
		try {
			cafeUserRepository.deleteById(username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
