package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService<User> {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User create(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}

	@Override
	public User update(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> read(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public List<User> readAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.existsById(id);
	}

	@Override
	public Optional<User> login(String email, String password) {
	    return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findbyEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public List<String> findRolesByUserId(Long iduser) {
		// TODO Auto-generated method stub
		return userRepository.findRolesByUserId(iduser);
	}

	
}
