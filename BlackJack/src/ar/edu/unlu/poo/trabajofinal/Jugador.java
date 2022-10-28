package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.enumerados.Evento;

public class Jugador extends Persona {
	
	private Mano segundaMano;
	private int dinero;
	private Apuesta apuesta;
	
	public Jugador(String nombre, int money) {
		
		super(nombre);
		this.apuesta = new Apuesta();
		this.setDinero(money);
		
	}

	public void darCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}

	// Si en un futuro implemento segundaMano, modificar.
	public void clearMano() {
		
		this.getManoActual().clear();
	
	}

	

	@Override
	public int getPuntaje() {
		
		return this.getManoActual().getPuntaje();
		
	}

	
	public int getDinero() {
		return dinero;
	}

	
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	
	public Apuesta getApuesta() {
		return apuesta;
	}

	
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}

	
	//public void splitMano() {}; Puede que en un futuro lo implemente, no es prioridad.
	
}
