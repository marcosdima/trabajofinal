package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Persona implements Puntuable{

	private String nombre;
	private Mano manoActual;
	
	public Persona(String nombre) {
		
		this.setNombre(nombre);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Mano getManoActual() {
		return manoActual;
	}
	
	public abstract int getPuntaje();
	
}
