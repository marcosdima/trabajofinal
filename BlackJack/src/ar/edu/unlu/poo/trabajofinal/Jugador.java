package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Jugador extends Persona implements Puntuable{

	
	private Mano manoActual;
	private boolean todaviaNoJugo;
	private boolean estaJugando;
	
	public Jugador(String nombre, int money) {
		
		super(nombre, money);
		manoActual = new Mano();
		this.estaJugando = true;
		
	}

	public void addCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}
	
	public Mano getManoActual() {
		return manoActual;
	}
	
	public int getPuntaje() {
		
		int res = 0;
		
		if (this.getManoActual().getCartas().size() > 2) {
			
			res = this.manoActual.getPuntaje();
			
		}
		else {
			
			for (Carta cartita : this.getManoActual().getCartas()) {
				
				if (cartita.esVisible()) {
					
					res += cartita.getValor();
					
				}
				
			}
			
		}
		
		return res;
		
	}

	public void clearMano() {
		
		this.getManoActual().clear();
	
	}
	
	public void mostrarCarta(int pos) {
		
		this.getManoActual().getCartas().get(pos).setVisibilidad(true);
		
	}
	
	public void mostrarCarta() {
		
		this.mostrarCarta(0);
		
	}
	
	public void mostrarCartas() {
		
		for (Carta carta : this.manoActual.getCartas()) {
			
			carta.setVisibilidad(true);
			
		}
		
	}
	
	
	//Estos son de la mano.

	public void setTodaviaNoJugo(boolean terminoTurno) {
		this.todaviaNoJugo = terminoTurno;
	}

	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}

	public void yaJugo() {
		
		this.setTodaviaNoJugo(false);
		
	}

	
	// Estos dos son de partida.
	public boolean sigueJugando() {
		
		return this.estaJugando;
		
	}
	
	public void noSigue() {
		
		this.estaJugando = false;
		
	}

}
