package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.UserRole;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findFirstByEmail(String email);

	User findByUserRole(UserRole admin);

}
