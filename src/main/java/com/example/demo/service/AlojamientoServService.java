package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface AlojamientoServService <AS> {
	AS create(AS as);
	AS update(AS as);
	void delete(Long id);
	Optional<AS> read(Long id);
	List<AS> readAll();
	boolean existsById(Long id);
}
