package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public class Mano extends ConjuntoDeCartas implements Puntuable {
	
	private static final int MAXIMO = 10;
	private EstadoDeMano estado;
	
	public Mano() {
		
		super(MAXIMO);
		this.estado = null;
		
	}
	
	public void clear() {};
	
	//Puede que convenga agregar una clase puntaje, de ahí resolver el tema del as.
	public int getPuntaje() {
		
		int puntos = 0;
		
		for (Carta c : this.getCartas()) {
			
			if (c.esVisible()) {
				
				puntos += c.getValor();
				
			}
			
		}

		return puntos;
		
	}

	
	public EstadoDeMano getEstado() {
		return estado;
	}

	
	public void setEstado(EstadoDeMano estado) {
		this.estado = estado;
	}

	
	
}
