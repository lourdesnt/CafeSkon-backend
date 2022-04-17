package com.example.cafeskon.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cafe_users", uniqueConstraints = { 
								@UniqueConstraint(columnNames = "username"),
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

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "cafeuser_roles", joinColumns = @JoinColumn(name = "cafeuser_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="role_id", referencedColumnName="id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

	public CafeUser() {
	
	}

	public CafeUser(@Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(min = 6) String password, Role role) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
