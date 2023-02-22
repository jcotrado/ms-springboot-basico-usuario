package com.convergenciax.service.entidades;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
public class Usuario {

	@javax.persistence.Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) // Id autoincrementable
	
	private int id;
	private String nombre;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
