package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, String> {

	List<User> findByName(String name);
}
