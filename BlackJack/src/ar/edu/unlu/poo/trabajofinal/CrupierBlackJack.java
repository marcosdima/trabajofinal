package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

public class CrupierBlackJack extends Crupier {

	private ArrayList<JugadorBlackJack> jugadores;
	
	public CrupierBlackJack(int nroDeJugadores) {
	
		super();
		this.setJugadores(nroDeJugadores);

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

	public void setJugadores(int n) {
		this.jugadores = new ArrayList<JugadorBlackJack>(n);
	}

	public boolean addJugador(String nombre, int plata) {
		
		JugadorBlackJack player = new JugadorBlackJack(nombre, plata);
		return this.jugadores.add(player);
		
	}

	public void terminarMano() {
		
		for (Jugador player : this.jugadores) {
			
			player.clearMano();
			
		}
		
		this.clearMano();
		
	}
			
}
