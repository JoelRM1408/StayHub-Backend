package com.example.demo.service;
import java.util.List;
import java.util.Optional;

public interface CategoriaService <C> {
	C create(C c);
	C update(C c);
	void delete(Long id);
	Optional<C> read(Long id);
	List<C> readAll();
	boolean existsById(Long id);
}
