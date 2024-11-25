package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Alojamiento;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento,Long> {
	@Query("SELECT DISTINCT a FROM Alojamiento a " +
	           "JOIN a.alojamientoserv ase " +
	           "WHERE (:idcategoria IS NULL OR a.categoria.idcategoria = :idcategoria) " +
	           "AND (:idservicios IS NULL OR ase.servicio.idservicio IN :idservicios)")
	    List<Alojamiento> filtrarAlojamientos(
	            @Param("idcategoria") Long idcategoria,
	            @Param("idservicios") List<Long> idservicios);
}	