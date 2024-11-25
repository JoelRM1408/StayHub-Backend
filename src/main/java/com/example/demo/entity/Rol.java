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
@NoArgsConstructor
@Entity
@Data
@Table(name="ROLES")
public class Rol {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrol;
	
	@Column(name = "ROL",nullable = false, unique = true)
    private String name;
	
	@Column(name = "IMG")
	private String image;
	
	@Column(name = "RUTA")
    private String route;
	
	@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore
	private Set<UserRol> useroles;
	
	@Builder
	public Rol(String name, String image, String route) {
		this.name = name;
		this.image = image;
		this.route = route;
	}
}

