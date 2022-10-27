package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Mano extends ConjuntoDeCartas implements Puntuable {
	
	public Mano(int tam) {
		
		super(tam);
		
	}
	
	public void clear() {};
	
	public int getPuntaje() {
		
		int puntos = 0;
		
		return puntos;
		
	};
	
}
