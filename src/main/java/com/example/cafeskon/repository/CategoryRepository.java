package com.example.cafeskon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.Category;
import com.example.cafeskon.model.ECategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Optional<Category> findByName(ECategory name);
}
