package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

//Pensar si Observado deberías ser implementado por 'Jugador'.

public class Crupier extends Jugador {
	
	private ArrayList<Jugador> jugadores;
	private MazoDeNaipes mazo;
	
	public Crupier(ArrayList<Jugador> players) {
		
		// En un futuro podría darle dinero y plantear un final con eso.
		super("Crupier", 0);
		this.setJugadores(players);
		this.mazo = new MazoDeNaipes();
	
	}

	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void repartirPrimeraMano() {

		
		for (Jugador player : this.jugadores) {
			
			player.addCarta(this.mazo.agarrarCarta());
			player.addCarta(this.mazo.agarrarCarta());
			player.mostrarCarta();	
		}
		
		this.addCarta(this.mazo.agarrarCarta());
		this.addCarta(this.mazo.agarrarCarta());
		this.mostrarCarta();
		
	}

}
