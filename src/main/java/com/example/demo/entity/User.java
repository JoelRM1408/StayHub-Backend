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
@Table(name="USERS")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;
	
	@Column(name = "NOMBRES",nullable = false)
    private String name;
	
	@Column(name = "APELLIDOS",nullable = false)
    private String lastname;
	
	@Column(name = "NUMERO",nullable = false, unique = true)
    private String phone;
	
	@Column(name = "EMAIL",nullable = false, unique = true)
    private String email;
	
	@Column(name = "PASSWORD",nullable = false)
    private String password;
	
	@Column(name = "IMG")
	private String image;
	
	@Column(name = "SUBSCRIPCION_ACT", nullable = false)
    private Boolean actsubcription;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
	private Set<UserRol> useroles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
	private Set<Alojamiento> alojamientos;

	@Builder
	public User(String name, String lastname, String phone, String email, String password, String image,
			Boolean actsubcription) {
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.image = image;
		this.actsubcription = actsubcription;
	}	
}
