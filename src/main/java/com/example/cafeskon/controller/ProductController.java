package com.example.cafeskon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.Category;
import com.example.cafeskon.model.Product;
import com.example.cafeskon.repository.CategoryRepository;
import com.example.cafeskon.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/list")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(products::add);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryId") Integer id) {
		List<Product> products = new ArrayList<Product>();
		productRepository.findByCategory(id).forEach(products::add);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name") String name) {
		List<Product> products = new ArrayList<Product>();
		productRepository.findByNameEquals(name).forEach(products::add);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
	
	@PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        Optional<Product> actualProduct = productRepository.findById(id);
        if (actualProduct.isPresent()) {
        	Product productToEdit = actualProduct.get();
        	productToEdit.setName(product.getName());
        	productToEdit.setDescription(product.getDescription());
        	productToEdit.setPrice(product.getPrice());
        	productToEdit.setImage(product.getImage());
        	productToEdit.setCategory(category);
        	return new ResponseEntity<>(productRepository.save(productToEdit), HttpStatus.OK);
        } else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
