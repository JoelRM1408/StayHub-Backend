package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Entity
@Data
@NoArgsConstructor
@Table(name = "CATEGORIAS")
public class Categoria {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcategoria;
	
	@Column(name = "CATEGORIA",nullable = false)
    private String name;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Alojamiento> alojamientos;
	
	@Builder
	public Categoria(String name) {
		this.name = name;
	}
}
