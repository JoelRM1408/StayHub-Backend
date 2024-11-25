package com.example.demo.controller.general;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserRolDTO;
import com.example.demo.entity.Rol;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRol;
import com.example.demo.serviceImpl.RolServiceImpl;
import com.example.demo.serviceImpl.UserRolServiceImpl;
import com.example.demo.serviceImpl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiuserol")
public class UserRolController {
	@Autowired
	UserRolServiceImpl userolService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RolServiceImpl rolService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<UserRol> listaUserRol = userolService.readAll();
			if (listaUserRol.isEmpty()) {
				String Message = "Esta lista de roles de usuario esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<UserRol>>(listaUserRol, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!userolService.existsById(id)) {
	  		String errorMessage = "No se encontro rol de usuario con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	UserRol userol = userolService.read(id).get();
	    	return new ResponseEntity<UserRol>(userol,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/insertar")
    public ResponseEntity<?> create(@RequestBody UserRolDTO userolDTO){
		try {
			Optional<User> user = userService.read(userolDTO.getIdUser());
			Optional<Rol> rol = rolService.read(userolDTO.getIdRol());
        	UserRol userol = new UserRol(
        			user.get(),
        			rol.get()
        		);
        	userolService.create(userol);
            return new ResponseEntity<UserRol>(userol, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserRolDTO userolDTO){
		if (!userolService.existsById(id)) {
	  		String errorMessage = "No se encontro rol de usuario con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
		Optional<User> user = userService.read(userolDTO.getIdUser());
		Optional<Rol> rol = rolService.read(userolDTO.getIdRol());
		
		UserRol userol = userolService.read(id).get();
		userol.setUser(user.get());
		userol.setRol(rol.get());
		userolService.update(userol);
	    String errorMessage = "Rol del usuario actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!userolService.existsById(id)) {
	  		String errorMessage = "No se encontro rol de usuario con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			userolService.delete(id);
			String errorMessage = "Rol de usuario con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}