package com.example.cafeskon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modelo Review correspondiente a review de producto
 * 
 * @author Lourdes Navarro
 *
 */
@Entity
@Table(name = "reviews")
public class Review {
	
	/**
	 * Atributo correspondiente al ID de la review (tipo Integer)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Atributo correspondiente al nombre de usuario de la persona que ha creado la review (tipo String)
	 */
	@Column(name = "username", nullable = false, length = 100)
    private String username;
	
	/**
	 * Atributo correspondiente al comentario de la review (tipo String)
	 */
	@Column(name = "user_comment", nullable = false, length = 1000)
    private String comment;
	
	/**
	 * Atributo correspondiente al rating de la review (tipo int)
	 */
	@Column(name="rating", columnDefinition = "int default 0")
    private int rating;

	/**
	 * Constructor predeterminado
	 */
	public Review() {
		
	}

	/**
	 * Constructor parametrizado
	 * @param username Nombre de usuario de la persona que ha creado la review
	 * @param comment Comentario de la review
	 * @param rating Rating de la review
	 */
	public Review(String username, String comment, int rating) {
		super();
		this.username = username;
		this.comment = comment;
		this.rating = rating;
	}
	
	//GETTERS Y SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
