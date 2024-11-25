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
public class DireccionDTO {
	private String direccion;
	private BigDecimal lat;
	private BigDecimal lng;
}
