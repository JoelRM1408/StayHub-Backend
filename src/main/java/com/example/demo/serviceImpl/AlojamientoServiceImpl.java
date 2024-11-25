package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alojamiento;
import com.example.demo.repository.AlojamientoRepository;
import com.example.demo.service.AlojamientoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlojamientoServiceImpl implements AlojamientoService<Alojamiento>{
	@Autowired
	AlojamientoRepository alojamientoRepository;

	@Override
	public Alojamiento create(Alojamiento a) {
		// TODO Auto-generated method stub
		return alojamientoRepository.save(a);
	}

	@Override
	public Alojamiento update(Alojamiento a) {
		// TODO Auto-generated method stub
		return alojamientoRepository.save(a);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		alojamientoRepository.deleteById(id);
	}

	@Override
	public Optional<Alojamiento> read(Long id) {
		// TODO Auto-generated method stub
		return alojamientoRepository.findById(id);
	}

	@Override
	public List<Alojamiento> readAll() {
		// TODO Auto-generated method stub
		return alojamientoRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return alojamientoRepository.existsById(id);
	}

	@Override
	public List<Alojamiento> filtrarAlojamientos(Long idcategoria, List<Long> idservicios) {
		// TODO Auto-generated method stub
		return alojamientoRepository.filtrarAlojamientos(idcategoria, idservicios);
	}
}
