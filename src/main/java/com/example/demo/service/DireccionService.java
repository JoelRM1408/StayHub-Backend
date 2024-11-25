package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface DireccionService <D>{
	D create(D d);
	D update(D d);
	void delete(Long id);
	Optional<D> read(Long id);
	List<D> readAll();
	boolean existsById(Long id);
}
