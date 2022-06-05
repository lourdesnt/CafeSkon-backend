package com.example.cafeskon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.ECategory;
import com.example.cafeskon.model.Product;

/**
 * Repositorio para productos (Product)
 * 
 * @author Lourdes Navarro
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	/**
	 * Método para encontrar producto por nombre
	 * @param name Nombre del producto
	 * @return producto
	 */
	List<Product> findByNameEquals(String name);
	
	/**
	 * Método para encontrar producto por categoria
	 * @param category Categoria de producto
	 * @return producto
	 */
	List<Product> findByCategory(ECategory category);
}
