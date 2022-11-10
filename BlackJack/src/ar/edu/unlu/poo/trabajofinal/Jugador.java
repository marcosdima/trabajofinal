package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Jugador extends Persona implements Puntuable{

	
	private Mano manoActual;
	private boolean todaviaNoJugo;
	private boolean estaJugando;
	
	public Jugador(String nombre, int money) {
		
		super(nombre, money);
		this.setManoActual();
		this.estaJugando = true;
		
	}

	public void addCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}
	
	public abstract int getPuntaje();

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

	public DatosDeJugador getData() {
		
		DatosDeJugador data = new DatosDeJugador(this);
		return data;
		
	}

	public int getNroCartas() {
		
		return this.getManoActual().getCartas().size();
		
	}
	
	//Estos son de la mano.

	public Mano getManoActual() {
		return manoActual;
	}
	
	public void setManoActual() {
		
		this.manoActual = new Mano();
		
	} 
	
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
