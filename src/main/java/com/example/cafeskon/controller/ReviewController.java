package com.example.cafeskon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.Review;
import com.example.cafeskon.repository.ProductRepository;
import com.example.cafeskon.repository.ReviewRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/{productId}")
	public ResponseEntity<List<Review>> getAllImagesByUsername(@PathVariable("productId") Integer productId) {
		try {
			if (!productRepository.existsById(productId)) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }
			
			List<Review> reviews = reviewRepository.findByProduct_Id(productId);

			if (reviews.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{productId}/new")
	public ResponseEntity<Review> createReview(@PathVariable("productId") Integer productId, @RequestBody Review newReview) {
		try {
			Review review = productRepository.findById(productId).map(product -> {
			      newReview.setProduct(product); return reviewRepository.save(newReview);}).get();
			
			return new ResponseEntity<>(review, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("review/{id}")
	public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") Integer id) {
		try {
			reviewRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
