package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

//Pensar si Observado deberías ser implementado por 'Jugador'.

public abstract class Crupier extends Jugador {

	private Mazo mazo;
	
	public Crupier() {
		
		// En un futuro podría darle dinero y plantear un final con eso.
		super("Crupier", 0);
		this.setMazo();
	
	}
	
	public void barajar() {
		
		this.mazo.barajar();
		
	};
	
	public void setMazo(Mazo mazo) {
		
		this.mazo = mazo;
		
	}
	
	public abstract void setMazo();
	
	public void terminarMano(ArrayList<Jugador> jugadores) {
		
		for (Jugador player : jugadores) {
			
			player.clearMano();
			
		}
		
		this.clearMano();
		
	}
	
	public void darCarta(Jugador player) {
		
		Carta cartita = this.mazo.agarrarCarta();
		player.addCarta(cartita);
		
	}

	
	public Mazo getMazo() {
		return mazo;
	}
	
}
