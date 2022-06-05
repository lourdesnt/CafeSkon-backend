package com.example.cafeskon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Modelo CafeUsers correspondiente a usuario
 * 
 * @author Lourdes Navarro Tocón
 *
 */
@Entity
@Table(name = "cafe_users", uniqueConstraints = { 
								@UniqueConstraint(columnNames = "email")})
public class CafeUser {

	/**
	 * Atributo correspondiente al nombre de usuario (tipo String)
	 */
	@Id
	@Size(max = 20)
	private String username;

	/**
	 * Atributo correspondiente al email del usuario (tipo String)
	 */
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	/**
	 * Atributo correspondiente a la contrasena del usuario (tipo String)
	 */
	@NotBlank
	@Size(min = 6)
	private String password;

	/**
	 * Atributo correspondiente al rol del usuario (tipo ERole)
	 */
	@NotNull
	@Column(columnDefinition = "ENUM('ADMIN','USER')")
    @Enumerated(EnumType.STRING)
	private ERole role;
	
	/**
	 * Constructor predeterminado
	 */
	public CafeUser() {
	
	}
	
	/**
	 * Constructor parametrizado
	 * @param username Nombre de usuario
	 * @param email Email del usuario
	 * @param password Contraseña del usuario
	 * @param role Rol del usuario
	 */
	public CafeUser(@Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(min = 6) String password, @NotNull ERole role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	//GETTERS Y SETTERS

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}
	
	
}
