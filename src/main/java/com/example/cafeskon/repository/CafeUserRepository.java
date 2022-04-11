package com.example.cafeskon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.CafeUser;

@Repository
public interface CafeUserRepository extends JpaRepository<CafeUser, String>{
	Optional<CafeUser> findByUsername(String username);
}
