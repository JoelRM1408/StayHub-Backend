package com.example.demo.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlojamientoDTO {
	private String nombre;
	private String desc;
	private BigDecimal precio;
	private String image1;
	private String image2;
	private String image3;
	private Long idUser;
	private Long idCategoria;
	private Long idDireccion;

}
