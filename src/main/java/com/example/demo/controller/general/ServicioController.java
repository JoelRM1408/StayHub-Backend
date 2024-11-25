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

import com.example.demo.DTO.ServicioDTO;
import com.example.demo.entity.Servicio;
import com.example.demo.serviceImpl.ServicioServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiservicio")
public class ServicioController {
	@Autowired
	ServicioServiceImpl servicioService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<Servicio> listaServ = servicioService.readAll();
			if (listaServ.isEmpty()) {
				String Message = "Esta lista de servicios esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Servicio>>(listaServ, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarxaloj/{idalojamiento}")
	public ResponseEntity<?> getByAlojamientoId(@PathVariable("idalojamiento") Long idalojamiento){
		try {
			List<Servicio> listaServ = servicioService.findServByAlojamientoId(idalojamiento);
			if (listaServ.isEmpty()) {
				String Message = "No hay servicios en este alojamiento";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Servicio>>(listaServ, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar servicios de alojamiento: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!servicioService.existsById(id)) {
	  		String errorMessage = "No se encontro servicio con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	Servicio serv = servicioService.read(id).get();
	    	return new ResponseEntity<Servicio>(serv,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ServicioDTO servicioDTO){
        try {
        	Servicio serv = new Servicio(
        			servicioDTO.getName(),
        			servicioDTO.getDesc()
        		);
        	servicioService.create(serv);
            return new ResponseEntity<Servicio>(serv, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ServicioDTO servicioDTO){
	    if (!servicioService.existsById(id)) {
	    	String errorMessage = "No se encontro servicio con el id "+id;
	    	return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
	    Servicio serv = servicioService.read(id).get();
	    serv.setName(servicioDTO.getName());
	    serv.setDesc(servicioDTO.getDesc());
	    servicioService.update(serv);
	    String errorMessage = "Servicio actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!servicioService.existsById(id)) {
	  		String errorMessage = "No se encontro servicio con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			servicioService.delete(id);
			String errorMessage = "Servicio con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
