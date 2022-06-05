package com.example.cafeskon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.CafeUser;

/**
 * Repositorio para usuarios (CafeUser)
 * 
 * @author Lourdes Navarro
 *
 */
@Repository
public interface CafeUserRepository extends JpaRepository<CafeUser, String>{
	
	/**
	 * Método para encontrar usuario por su nombre de usuario
	 * @param username Nombre de usuario del usuario a obtener
	 * @return usuario
	 */
	Optional<CafeUser> findByUsername(String username);
}
