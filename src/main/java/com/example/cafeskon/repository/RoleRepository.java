package com.example.cafeskon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeskon.model.ERole;
import com.example.cafeskon.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	public Optional<Role> findByName(ERole name);
}
