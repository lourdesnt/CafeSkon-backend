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

import com.example.cafeskon.model.Product;
import com.example.cafeskon.model.Review;
import com.example.cafeskon.repository.ProductRepository;
import com.example.cafeskon.repository.ReviewRepository;

/**
 * Controlador para reviews de producto (Review)
 * 
 * @author Lourdes Navarro
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/reviews")
public class ReviewController {

	/**
	 * Repositorio de reviews (tipo ReviewRepository)
	 */
	@Autowired
	private ReviewRepository reviewRepository;

	/**
	 * Repositorio de productos (tipo ProductRepository)
	 */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Método GET para obtener todas las reviews de un producto
	 * @param productId ID del producto
	 * @return respuesta de la llamada
	 */
	@GetMapping("/{productId}")
	public ResponseEntity<List<Review>> getAllReviewsByProduct(@PathVariable("productId") Integer productId) {
		try {
			if (!productRepository.existsById(productId)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Review> reviews = productRepository.findById(productId).get().getReviews();

			if (reviews.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Método POST para crear una nueva review
	 * @param productId ID del producto
	 * @param newReview Nueva review
	 * @return respuesta de la llamada
	 */
	@PostMapping("{productId}/new")
	public ResponseEntity<Review> createReview(@PathVariable("productId") Integer productId, @RequestBody Review newReview) {
		try {
			Product product = productRepository.findById(productId).get();
			product.getReviews().add(newReview);
			reviewRepository.saveAll(product.getReviews());
			productRepository.saveAndFlush(product);
			System.out.println("Review created");
			return new ResponseEntity<>(newReview, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Método DELETE para eliminar una review
	 * @param id ID de la review a eliminar
	 * @return respuesta de la llamada
	 */
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
