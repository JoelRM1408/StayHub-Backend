package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Servicio;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioServiceImpl implements ServicioService<Servicio>{
	@Autowired
	ServicioRepository servicioRepository;
	
	@Override
	public Servicio create(Servicio s) {
		// TODO Auto-generated method stub
		return servicioRepository.save(s);
	}

	@Override
	public Servicio update(Servicio s) {
		// TODO Auto-generated method stub
		return servicioRepository.save(s);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		servicioRepository.deleteById(id);
	}

	@Override
	public Optional<Servicio> read(Long id) {
		// TODO Auto-generated method stub
		return servicioRepository.findById(id);
	}

	@Override
	public List<Servicio> readAll() {
		// TODO Auto-generated method stub
		return servicioRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return servicioRepository.existsById(id);
	}

	@Override
	public List<Servicio> findServByAlojamientoId(Long idalojamiento) {
		// TODO Auto-generated method stub
		return servicioRepository.findServByAlojamientoId(idalojamiento);
	}

}
