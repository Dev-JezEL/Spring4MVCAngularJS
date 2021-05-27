package com.proyecto.springmvc.model;

public class PersonaFisica extends Persona{
	private long id;
	
	private String nombre;
	
	public PersonaFisica() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
