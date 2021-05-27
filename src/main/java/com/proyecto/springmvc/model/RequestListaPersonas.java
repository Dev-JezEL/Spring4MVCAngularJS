package com.proyecto.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class RequestListaPersonas {
	List<PersonaFisica> listaPersonas = new ArrayList<>();

	public List<PersonaFisica> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaFisica> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	
	
}
