package com.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}