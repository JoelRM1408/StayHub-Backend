package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Servicio;

public interface ServicioService <S> {
	S create(S s);
	S update(S s);
	void delete(Long id);
	Optional<S> read(Long id);
	List<S> readAll();
	boolean existsById(Long id);
	List<Servicio> findServByAlojamientoId(Long idalojamiento);
}
