package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Alojamiento;

public interface AlojamientoService <A> {
	A create(A a);
	A update(A a);
	void delete(Long id);
	Optional<A> read(Long id);
	List<A> readAll();
	boolean existsById(Long id);
	 List<Alojamiento> filtrarAlojamientos(Long idcategoria, List<Long> idservicios);

}
