package com.example.demo.controller.general;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.security.JwtUtils;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.serviceImpl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")	
@RestController
@RequestMapping("/apiuser")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("roles", userDetails.getAuthorities());
            response.put("message", "Autenticación exitosa");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
        }
    }
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<User> listaUser = userService.readAll();
			if (listaUser.isEmpty()) {
				String Message = "Esta lista de usuarios esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<User>>(listaUser, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!userService.existsById(id)) {
	  		String errorMessage = "No se encontro el usuario con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	User user = userService.read(id).get();
	    	return new ResponseEntity<User>(user,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/registrar")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
        try {
        	User user = new User(
        		    userDTO.getName(),
        		    userDTO.getLastname(),
        		    userDTO.getPhone(),
        		    userDTO.getEmail(),
                    passwordEncoder.encode(userDTO.getPassword()), 
        		    userDTO.getImage(),
        		    userDTO.getActsubcription()
        		);
    		userService.create(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al registrar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
		if (!userService.existsById(id)) {
			String errorMessage = "No se encontro el usuario con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
	    User user = userService.read(id).get();
	    user.setName(userDTO.getName());
	    user.setLastname(userDTO.getLastname());
	    user.setPhone(userDTO.getPhone());
	    user.setEmail(userDTO.getEmail());
	    user.setImage(userDTO.getImage());
	    user.setActsubcription(userDTO.getActsubcription());
	    
	    if (!userDTO.getPassword().equals(user.getPassword())) {
	        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
	        user.setPassword(encryptedPassword);
	    }
	    userService.update(user);
	    String errorMessage = "Usuario actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!userService.existsById(id)) {
	  		String errorMessage = "No se encontro el usuario con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			userService.delete(id);
			String errorMessage = "Usuario con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}	
}	
