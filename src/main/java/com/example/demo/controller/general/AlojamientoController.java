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

import com.example.demo.DTO.AlojamientoDTO;
import com.example.demo.DTO.FiltroDTO;
import com.example.demo.entity.Alojamiento;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Direccion;
import com.example.demo.entity.User;
import com.example.demo.serviceImpl.AlojamientoServiceImpl;
import com.example.demo.serviceImpl.CategoriaServiceImpl;
import com.example.demo.serviceImpl.DireccionServiceImpl;
import com.example.demo.serviceImpl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")	
@RestController
@RequestMapping("/apialojamiento")
public class AlojamientoController {
	@Autowired
	AlojamientoServiceImpl alojamientoService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	DireccionServiceImpl direccionService;
	
	@Autowired
	CategoriaServiceImpl categoriaService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<Alojamiento> listaAloj = alojamientoService.readAll();
			if (listaAloj.isEmpty()) {
				String Message = "Esta lista de alojamientos esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Alojamiento>>(listaAloj, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!alojamientoService.existsById(id)) {
	  		String errorMessage = "No se encontro el alojamiento con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	Alojamiento aloj = alojamientoService.read(id).get();
	    	return new ResponseEntity<Alojamiento>(aloj,HttpStatus.OK);
	    }
	}
	
	@GetMapping("/filtrar")
	public ResponseEntity<?> filtrarAlojamientos(@RequestBody FiltroDTO filtroDTO) {
	    try {
	        List<Alojamiento> listaAlojamientos = alojamientoService.filtrarAlojamientos(filtroDTO.getIdcategoria(), filtroDTO.getIdservicios());

	        if (listaAlojamientos.isEmpty()) {
	            String message = "No se encontraron alojamientos con los filtros proporcionados.";
	            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<>(listaAlojamientos, HttpStatus.OK);
	    } catch (Exception e) {
	        String errorMessage = "Error al filtrar alojamientos: " + e.getMessage();
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/registrar")
    public ResponseEntity<?> create(@RequestBody AlojamientoDTO alojamientoDTO){
        try {
        	Optional<User> user = userService.read(alojamientoDTO.getIdUser());
			Optional<Categoria> cateogoria = categoriaService.read(alojamientoDTO.getIdCategoria());
        	Optional<Direccion> direccion = direccionService.read(alojamientoDTO.getIdDireccion());

        	Alojamiento aloj = new Alojamiento(
        			alojamientoDTO.getNombre(),
        			alojamientoDTO.getDesc(),
        			alojamientoDTO.getPrecio(),
        			alojamientoDTO.getImage1(),
        			alojamientoDTO.getImage2(),
        			alojamientoDTO.getImage3(), 
        			user.get(),
        			cateogoria.get(),
        			direccion.get()
        		);
        	alojamientoService.create(aloj);
            return new ResponseEntity<Alojamiento>(aloj, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al registrar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AlojamientoDTO alojamientoDTO){
		if (!alojamientoService.existsById(id)) {
			String errorMessage = "No se encontro el alojamiento con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
		
		Optional<User> user = userService.read(alojamientoDTO.getIdUser());
		Optional<Categoria> categoria = categoriaService.read(alojamientoDTO.getIdCategoria());
    	Optional<Direccion> direccion = direccionService.read(alojamientoDTO.getIdDireccion());
    	
		Alojamiento aloj = alojamientoService.read(id).get();
		aloj.setNombre(alojamientoDTO.getNombre());
		aloj.setDesc(alojamientoDTO.getDesc());
		aloj.setPrecio(alojamientoDTO.getPrecio());
		aloj.setImage1(alojamientoDTO.getImage1());
		aloj.setImage2(alojamientoDTO.getImage2());
		aloj.setImage3(alojamientoDTO.getImage3());
		aloj.setUser(user.get());
		aloj.setCategoria(categoria.get());
		aloj.setDireccion(direccion.get());
	  
	    alojamientoService.update(aloj);
	    String errorMessage = "Alojamiento actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!alojamientoService.existsById(id)) {
	  		String errorMessage = "No se encontro el alojamiento con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			alojamientoService.delete(id);
			String errorMessage = "Alojamiento con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
