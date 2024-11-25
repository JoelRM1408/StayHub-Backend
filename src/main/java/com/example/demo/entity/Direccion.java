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
@Table(name = "DIRECCIONES")
public class Direccion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idirec;
	
	@Column(name = "DIRECCION", nullable = false)
    private String direccion;
	
	@Column(name = "LATITUD",precision = 10, scale = 8, nullable = false)
    private BigDecimal lat;

    @Column(name = "LONGITUD",precision = 10, scale = 8, nullable = false)
    private BigDecimal lng;
    
    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Alojamiento> alojamientos;

    @Builder
	public Direccion(String direccion, BigDecimal lat, BigDecimal lng) {
		this.direccion = direccion;
		this.lat = lat;
		this.lng = lng;
	}
}
