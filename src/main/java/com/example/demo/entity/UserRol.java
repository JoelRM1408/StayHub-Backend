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
@Table(name="USER_ROLES")
public class UserRol {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduserol;
	
	@ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)	
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "ROL_ID", nullable = false)	
	private Rol rol;
	
	
	@Builder
	public UserRol(User user, Rol rol) {
		this.user = user;
		this.rol = rol;
	}
}
