package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long> {
	@Query("SELECT ase.servicio FROM AlojamientoServicio ase WHERE ase.alojamiento.idalojamiento = :idalojamiento")
    List<Servicio> findServByAlojamientoId(@Param("idalojamiento") Long idalojamiento);
}