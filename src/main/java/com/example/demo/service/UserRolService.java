package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface UserRolService<UR> {
	UR create(UR ur);
	UR update(UR ur);
	void delete(Long id);
	Optional<UR> read(Long id);
	List<UR> readAll();
	boolean existsById(Long id);
}
