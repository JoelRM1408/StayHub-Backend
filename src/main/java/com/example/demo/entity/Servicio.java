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
@Table(name = "SERVICIOS")
public class Servicio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idservicio;
	
	@Column(name = "SERVICIO",nullable = false)
    private String name;
	
	@Column(name = "DESCRIPCION")
    private String desc;
	
	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AlojamientoServicio> alojamientoserv;
	
	@Builder
	public Servicio(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
}
