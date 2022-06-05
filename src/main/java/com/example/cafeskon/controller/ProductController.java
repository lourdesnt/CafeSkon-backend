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

import com.example.cafeskon.model.ECategory;
import com.example.cafeskon.model.Product;
import com.example.cafeskon.repository.ProductRepository;

/**
 * Controlador para productos (Product)
 * 
 * @author Lourdes Navarro
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {
	
	/**
	 * Repositorio de productos (tipo ProductRepository)
	 */
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Método GET para obtener todos los productos
	 * @return respuesta de la llamada
	 */
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
	
	/**
	 * Método GET para obtener producto por ID
	 * @param id ID del producto
	 * @return respuesta de la llamada
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Método GET para obtener productos por categoria
	 * @param category Categoria de producto
	 * @return respuesta de la llamada
	 */
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("category") ECategory category) {
		List<Product> products = new ArrayList<Product>();
		productRepository.findByCategory(category).forEach(products::add);
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
	
	/**
	 * Método GET para obtener producto por nombre
	 * @param name Nombre del producto
	 * @return respuesta de la llamada
	 */
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
	
	/**
	 * Método POST para crear un nuevo producto
	 * @param product Nuevo producto
	 * @return respuesta de la llamada
	 */
	@PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	/**
	 * Método PUT para modificar un producto
	 * @param id ID del producto a modificar
	 * @param product Producto modificado
	 * @return respuesta de la llamada
	 */
	@PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        Optional<Product> actualProduct = productRepository.findById(id);
        if (actualProduct.isPresent()) {
        	Product productToEdit = actualProduct.get();
        	productToEdit.setName(product.getName());
        	productToEdit.setDescription(product.getDescription());
        	productToEdit.setPrice(product.getPrice());
        	productToEdit.setImage(product.getImage());
        	productToEdit.setCategory(product.getCategory());
        	return new ResponseEntity<>(productRepository.save(productToEdit), HttpStatus.OK);
        } else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	/**
	 * Método DELETE para eliminar un producto
	 * @param id ID del producto a eliminar
	 * @return respuesta de la llamada
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
