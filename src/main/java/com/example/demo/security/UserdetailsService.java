package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.demo.entity.User;
import com.example.demo.serviceImpl.UserServiceImpl;

@Service
public class UserdetailsService implements UserDetailsService{
	
    private final UserServiceImpl userService;

    @Autowired
    public UserdetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }
	
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findbyEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }
        
        List<String> roles = userService.findRolesByUserId(user.getIduser());
        return new Userdetails(user,roles);
    }

}
