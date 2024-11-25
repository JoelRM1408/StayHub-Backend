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

import com.example.demo.serviceImpl.ServicioServiceImpl;
import com.example.demo.DTO.AlojamientoServDTO;
import com.example.demo.entity.Alojamiento;
import com.example.demo.entity.AlojamientoServicio;
import com.example.demo.entity.Servicio;
import com.example.demo.serviceImpl.AlojamientoServServiceImpl;
import com.example.demo.serviceImpl.AlojamientoServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apialojserv")
public class AlojamientoServController {
	@Autowired
	AlojamientoServServiceImpl alojamientoServService;
	
	@Autowired
	AlojamientoServiceImpl alojamientoService;
	
	@Autowired
	ServicioServiceImpl servicioService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<AlojamientoServicio> listaAlojServ = alojamientoServService.readAll();
			if (listaAlojServ.isEmpty()) {
				String Message = "Esta lista de servicios de alojamientos esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<AlojamientoServicio>>(listaAlojServ, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!alojamientoServService.existsById(id)) {
	  		String errorMessage = "No se encontro servicio de alojamiento con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	AlojamientoServicio aljserv = alojamientoServService.read(id).get();
	    	return new ResponseEntity<AlojamientoServicio>(aljserv,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/insertar")
    public ResponseEntity<?> create(@RequestBody AlojamientoServDTO alojservDTO){
		try {
			Optional<Alojamiento> aloj = alojamientoService.read(alojservDTO.getIdAlojamiento());
			Optional<Servicio> serv = servicioService.read(alojservDTO.getIdServicio());
			AlojamientoServicio alojserv = new AlojamientoServicio(
					aloj.get(),
					serv.get()
        		);
			alojamientoServService.create(alojserv);
            return new ResponseEntity<AlojamientoServicio>(alojserv, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AlojamientoServDTO alojservDTO){
		if (!alojamientoServService.existsById(id)) {
	  		String errorMessage = "No se encontro servicio de alojamiento con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
		Optional<Alojamiento> aloj = alojamientoService.read(alojservDTO.getIdAlojamiento());
		Optional<Servicio> serv = servicioService.read(alojservDTO.getIdServicio());
		
		AlojamientoServicio alojserv = alojamientoServService.read(id).get();
		alojserv.setAlojamiento(aloj.get());
		alojserv.setServicio(serv.get());
		alojamientoServService.update(alojserv);
	    String errorMessage = "Servicio de alojamiento actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!alojamientoServService.existsById(id)) {
	  		String errorMessage = "No se encontro servicio de alojamiento con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			alojamientoServService.delete(id);
			String errorMessage = "Servicio de alojamiento con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
