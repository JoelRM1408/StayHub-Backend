package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Data
@Table(name = "ALOJAMIENTOS_SERVICIOS")
public class AlojamientoServicio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalserv;
	
	@ManyToOne
    @JoinColumn(name = "ALOJAMIENTO_ID", nullable = false)	
	private Alojamiento alojamiento;
	
	@ManyToOne
    @JoinColumn(name = "SERVICIO_ID", nullable = false)	
	private Servicio servicio;

	@Builder
	public AlojamientoServicio(Alojamiento alojamiento, Servicio servicio) {
		this.alojamiento = alojamiento;
		this.servicio = servicio;
	}
}
