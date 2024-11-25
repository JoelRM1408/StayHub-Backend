package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserRol;
import com.example.demo.repository.UserRolRepository;
import com.example.demo.service.UserRolService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRolServiceImpl implements UserRolService<UserRol>{
	@Autowired
	UserRolRepository userolRepository;
	
	@Override
	public UserRol create(UserRol ur) {
		// TODO Auto-generated method stub
		return userolRepository.save(ur);
	}

	@Override
	public UserRol update(UserRol ur) {
		// TODO Auto-generated method stub
		return userolRepository.save(ur);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userolRepository.deleteById(id);
	}

	@Override
	public Optional<UserRol> read(Long id) {
		// TODO Auto-generated method stub
		return userolRepository.findById(id);
	}

	@Override
	public List<UserRol> readAll() {
		// TODO Auto-generated method stub
		return userolRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return userolRepository.existsById(id);
	}
	
}
