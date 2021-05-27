package com.proyecto.springmvc.model;

public class User {

	private long id;
	
	private String nombre;
	
	private String entidad;
	
	private String municipio;
	
	public User(){
		id=0;
	}
	
	public User(long id, String nombre, String entidad, String municipio){
		this.id = id;
		this.nombre = nombre;
		this.entidad = entidad;
		this.municipio = municipio;
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

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", entidad=" + entidad
				+ ", municipio=" + municipio + "]";
	}
	

	
}
