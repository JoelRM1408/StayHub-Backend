package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ALOJAMIENTOS")
public class Alojamiento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalojamiento;
	
	@Column(name = "NOMBRE",nullable = false)
    private String nombre;
	
	@Column(name = "DESCRIPCION")
    private String desc;
	
	@Column(name = "PRECIO",precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
	
	@Column(name = "IMAGE1")
    private String image1;
	
	@Column(name = "IMAGE2")
    private String image2;
	
	@Column(name = "IMAGE3")
    private String image3;
	
	@ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)	
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "CATEGORIA_ID", nullable = false)	
	private Categoria categoria;
	
	@ManyToOne
    @JoinColumn(name = "DIRECCION_ID", nullable = false)	
	private Direccion direccion;
	
	@OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AlojamientoServicio> alojamientoserv;

	@Builder
	public Alojamiento(String nombre, String desc, BigDecimal precio, String image1, String image2, String image3,
			User user, Categoria categoria, Direccion direccion) {
		this.nombre = nombre;
		this.desc = desc;
		this.precio = precio;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.user = user;
		this.categoria = categoria;
		this.direccion = direccion;
	}
}
