package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface RolService <R> {
	R create(R r);
	R update(R r);
	void delete(Long id);
	Optional<R> read(Long id);
	List<R> readAll();
	boolean existsById(Long id);
}
