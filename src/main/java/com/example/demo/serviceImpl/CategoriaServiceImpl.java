package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.CategoriaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements  CategoriaService<Categoria>{
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public Categoria create(Categoria c) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(c);
	}

	@Override
	public Categoria update(Categoria c) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categoriaRepository.deleteById(id);
	}

	@Override
	public Optional<Categoria> read(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id);
	}

	@Override
	public List<Categoria> readAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.existsById(id);
	}
}
