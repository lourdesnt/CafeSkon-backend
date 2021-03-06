package com.example.cafeskon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Modelo Product correspondiente a producto
 * 
 * @author Lourdes Navarro
 *
 */
@Entity
@Table(name = "products")
public class Product {

	/**
	 * Atributo correspondiente al ID del producto (tipo Integer)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * Atributo correspondiente al nombre del producto (tipo String)
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Atributo correspondiente a la descripcion del producto (tipo String)
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * Atributo correspondiente al precio del producto (tipo double)
	 */
	@Column(name = "price")
	private double price;
	
	/**
	 * Atributo correspondiente a la imagen del producto (tipo String)
	 */
	@Column(name = "prod_image")
    private String image;
	
	/**
	 * Atributo correspondiente a la lista de reviews del producto
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
    private List<Review> reviews;
	
	/**
	 * Atributo correspondiente a la categoria del producto (tipo ECategory)
	 */
	@Column(columnDefinition = "ENUM('CAKE','COOKIE','DRINK','COFFEE')")
    @Enumerated(EnumType.STRING)
	private ECategory category;
	
	/**
	 * Constructor predeterminado
	 */
	public Product() {
		
	}

	/**
	 * Constructor parametrizado
	 * @param name Nombre del producto
	 * @param description Descripcion del producto
	 * @param price Precio del producto
	 * @param image Imagen del producto
	 * @param category Categoria del producto
	 */
	public Product(String name, String description, double price, String image, ECategory category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category = category;
	}
	
	//GETTERS Y SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public ECategory getCategory() {
		return category;
	}

	public void setCategory(ECategory category) {
		this.category = category;
	}

	
}
