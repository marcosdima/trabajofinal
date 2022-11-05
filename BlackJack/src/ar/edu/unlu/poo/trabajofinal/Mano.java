package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public class Mano extends ConjuntoDeCartas implements Puntuable {
	
	private static final int MAXIMO = 10;
	private EstadoMano estado;
	
	public Mano() {
		
		super(MAXIMO);
		this.setEstado(EstadoMano.NULO);
		
	}
	
	public void clear() {};
	
	//Puede que convenga agregar una clase puntaje, de ahí resolver el tema del as.
	public int getPuntaje() {
		
		int puntos = 0;
		
		for (Carta c : this.getCartas()) {
			
			puntos += c.getValor();
			
		}
		
		return puntos;
		
	}

	
	public EstadoMano getEstado() {
		return estado;
	}

	
	public void setEstado(EstadoMano estado) {
		this.estado = estado;
	};
	
	
	
}
