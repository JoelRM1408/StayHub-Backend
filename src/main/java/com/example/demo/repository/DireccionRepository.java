package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion,Long> {
	
}