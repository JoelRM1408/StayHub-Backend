package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface UserService <U> {
	U create(U u);
	U update(U u);
	void delete(Long id);
	Optional<U> read(Long id);
	List<U> readAll();
	boolean existsById(Long id);
	Optional<U> login(String email, String password);
	U findbyEmail(String email);
	List<String> findRolesByUserId(Long iduser);
}