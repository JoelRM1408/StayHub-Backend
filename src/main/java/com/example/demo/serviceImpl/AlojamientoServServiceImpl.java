package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.AlojamientoServicio;
import com.example.demo.repository.AlojamientoServRepository;
import com.example.demo.service.AlojamientoServService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlojamientoServServiceImpl implements AlojamientoServService<AlojamientoServicio>{
	@Autowired
	AlojamientoServRepository alojservRepository;

	@Override
	public AlojamientoServicio create(AlojamientoServicio as) {
		// TODO Auto-generated method stub
		return alojservRepository.save(as);
	}

	@Override
	public AlojamientoServicio update(AlojamientoServicio as) {
		// TODO Auto-generated method stub
		return alojservRepository.save(as);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		alojservRepository.deleteById(id);
	}

	@Override
	public Optional<AlojamientoServicio> read(Long id) {
		// TODO Auto-generated method stub
		return alojservRepository.findById(id);
	}

	@Override
	public List<AlojamientoServicio> readAll() {
		// TODO Auto-generated method stub
		return alojservRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return alojservRepository.existsById(id);
	}

}
