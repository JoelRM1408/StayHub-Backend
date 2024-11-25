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

import com.example.demo.DTO.DireccionDTO;
import com.example.demo.entity.Direccion;
import com.example.demo.serviceImpl.DireccionServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apidireccion")
public class DireccionController {
	@Autowired
	DireccionServiceImpl direccionService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<Direccion> listaDir = direccionService.readAll();
			if (listaDir.isEmpty()) {
				String Message = "Esta lista de direcciones esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Direccion>>(listaDir, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!direccionService.existsById(id)) {
	  		String errorMessage = "No se encontro direccion con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	Direccion dir = direccionService.read(id).get();
	    	return new ResponseEntity<Direccion>(dir,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DireccionDTO direccionDTO){
        try {
        	Direccion dir = new Direccion(
        			direccionDTO.getDireccion(),
        			direccionDTO.getLat(),
        			direccionDTO.getLng()
        		);
        	direccionService.create(dir);
            return new ResponseEntity<Direccion>(dir, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DireccionDTO direccionDTO){
	    if (!direccionService.existsById(id)) {
	    	String errorMessage = "No se encontro direccion con el id "+id;
	    	return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
	    Direccion dir = direccionService.read(id).get();
	    dir.setDireccion(direccionDTO.getDireccion());
	    dir.setLat(direccionDTO.getLat());
	    dir.setLng(direccionDTO.getLng());
	    direccionService.update(dir);
	    String errorMessage = "Direccion actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!direccionService.existsById(id)) {
	  		String errorMessage = "No se encontro direccion con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			direccionService.delete(id);
			String errorMessage = "Direccion con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
