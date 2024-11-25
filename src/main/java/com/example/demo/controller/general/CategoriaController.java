package com.example.demo.controller.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.entity.Categoria;
import com.example.demo.serviceImpl.CategoriaServiceImpl;

@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apicategoria")
public class CategoriaController {
	@Autowired
	CategoriaServiceImpl categoriaService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		try {
			List<Categoria> listaCat = categoriaService.readAll();
			if (listaCat.isEmpty()) {
				String Message = "Esta lista de categorias esta vacia";
				return new ResponseEntity<>(Message, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Categoria>>(listaCat, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = "Error al listar: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		if (!categoriaService.existsById(id)) {
	  		String errorMessage = "No se encontro categoria con el id "+id;
		    return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    } else {
	    	Categoria cat = categoriaService.read(id).get();
	    	return new ResponseEntity<Categoria>(cat,HttpStatus.OK);
	    }
	}
	
	@PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody CategoriaDTO categoriaDTO){
        try {
        	Categoria cat = new Categoria(
        			categoriaDTO.getName()
        		);
        	categoriaService.create(cat);
            return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);
        } catch (Exception e) {
        	String errorMessage = "Error al insertar: " + e.getMessage();
        	return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoriaDTO categoriaDTO){
	    if (!categoriaService.existsById(id)) {
	    	String errorMessage = "No se encontro categoria con el id "+id;
	    	return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	    }
	    Categoria cat = categoriaService.read(id).get();
	    cat.setName(categoriaDTO.getName());
	    categoriaService.update(cat);
	    String errorMessage = "Categoria actualizado exitosamente";
	    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if (!categoriaService.existsById(id)) {
	  		String errorMessage = "No se encontro categoria con el id "+id;
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);  		
		}else {
			categoriaService.delete(id);
			String errorMessage = "Categoria con el id "+id+" eliminado";
			return new ResponseEntity<>(errorMessage,HttpStatus.OK);  
		}	
	}
}
