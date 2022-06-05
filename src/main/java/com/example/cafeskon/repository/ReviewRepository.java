package com.example.cafeskon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.Review;

/**
 * Repositorio para reviews (Review)
 * 
 * @author Lourdes Navarro
 *
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	
}
