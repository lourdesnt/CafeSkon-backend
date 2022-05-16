package com.example.cafeskon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cafe_users", uniqueConstraints = { 
								@UniqueConstraint(columnNames = "email")})
public class CafeUser {

	@Id
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	@NotNull
	@Column(columnDefinition = "ENUM('ADMIN','USER')")
    @Enumerated(EnumType.STRING)
	private ERole role;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;

	public CafeUser() {
	
	}

	public CafeUser(@Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(min = 6) String password, @NotNull ERole role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
