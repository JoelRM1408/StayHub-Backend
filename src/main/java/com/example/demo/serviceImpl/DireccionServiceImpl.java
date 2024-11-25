package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Direccion;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.service.DireccionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionServiceImpl implements DireccionService<Direccion> {
	@Autowired
	DireccionRepository direccionRepository;

	@Override
	public Direccion create(Direccion d) {
		// TODO Auto-generated method stub
		return direccionRepository.save(d);
	}

	@Override
	public Direccion update(Direccion d) {
		// TODO Auto-generated method stub
		return direccionRepository.save(d);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		direccionRepository.deleteById(id);
	}

	@Override
	public Optional<Direccion> read(Long id) {
		// TODO Auto-generated method stub
		return direccionRepository.findById(id);
	}

	@Override
	public List<Direccion> readAll() {
		// TODO Auto-generated method stub
		return direccionRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return direccionRepository.existsById(id);
	}
}
