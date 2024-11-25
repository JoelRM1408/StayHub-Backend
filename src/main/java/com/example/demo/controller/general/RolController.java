package com.example.demo.controller.general;

import java.util.List;

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

import com.example.demo.DTO.RolDTO;
import com.example.demo.entity.Rol;
import com.example.demo.serviceImpl.RolServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apirol")
public class RolController {
	@Autowired
	RolServiceImpl rolService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<Rol> listaRol = rolService.readAll();
			if (listaRol.isEmpty()) {
				String Message = "Esta lista de roles esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Rol>>(listaRol, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!rolService.existsById(id)) {
	  		String errorMessage = "No se encontro rol con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	Rol rol = rolService.read(id).get();
	    	return new ResponseEntity<Rol>(rol,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/insertar")
    public ResponseEntity<?> create(@RequestBody RolDTO rolDTO){
        try {
        	Rol rol = new Rol(
        			rolDTO.getName(),
        			rolDTO.getImage(),
        			rolDTO.getRoute()
        		);
        	rolService.create(rol);
            return new ResponseEntity<Rol>(rol, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody RolDTO rolDTO){
	    if (!rolService.existsById(id)) {
	    	String errorMessage = "No se encontro rol con el id "+id;
	    	return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
	    Rol rol = rolService.read(id).get();
	    rol.setName(rolDTO.getName());
	    rol.setImage(rolDTO.getImage());
	    rol.setRoute(rolDTO.getRoute());
	    rolService.update(rol);
	    String errorMessage = "Rol actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!rolService.existsById(id)) {
	  		String errorMessage = "No se encontro rol con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			rolService.delete(id);
			String errorMessage = "Rol con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
