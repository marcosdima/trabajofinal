package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

public class CrupierBlackJack extends Crupier {

	private ArrayList<JugadorBlackJack> jugadores;
	
	public CrupierBlackJack(ArrayList<JugadorBlackJack> players) {
	
		super();
		this.setJugadores(players);

	}

	public void repartirPrimeraMano() {

		for (Jugador player : this.getJugadores()) {
			
			player.addCarta(this.getMazo().agarrarCarta());
			
			player.addCarta(this.getMazo().agarrarCarta());
			player.mostrarCarta();	
			
		}
		
		this.addCarta(this.getMazo().agarrarCarta());
		this.addCarta(this.getMazo().agarrarCarta());
		this.mostrarCarta();
		
	}

	public void mostrarCartas(Jugador player) {
	
		player.mostrarCarta(1);
		
	}

	public boolean puedeSeguirJugando(Jugador player) {
		
		boolean res = true;
		
		if (player.getPuntaje() >= 21) {
			
			res = false;
			
		}
		
		return res;
		
	}
	
	public void setMazo() {
		
		MazoDeNaipes m = new MazoDeNaipes();
		this.setMazo(m);
		
	}
	
	public ArrayList<JugadorBlackJack> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<JugadorBlackJack> jugadores) {
		this.jugadores = jugadores;
	}

	
}
