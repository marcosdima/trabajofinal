package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Jugador extends Persona implements Puntuable{

	
	private Mano manoActual;
	private boolean todaviaNoJugo;
	
	public Jugador(String nombre, int money) {
		
		super(nombre, money);
		manoActual = new Mano();
		
	}

	public void addCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}
	
	public Mano getManoActual() {
		return manoActual;
	}
	
	public int getPuntaje() {
		
		return this.manoActual.getPuntaje();
		
	};

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
	
	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}

	public void setTodaviaNoJugo(boolean terminoTurno) {
		this.todaviaNoJugo = terminoTurno;
	}

}
