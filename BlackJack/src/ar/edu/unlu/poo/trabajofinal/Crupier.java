package ar.edu.unlu.poo.trabajofinal;

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
	
	protected abstract void setMazo();
	
	protected abstract boolean reiniciarMano();
	
	public void darCarta(Jugador player) {
		
		Carta cartita = this.mazo.agarrarCarta();
		player.addCarta(cartita);
		
	}

	public Mazo getMazo() {
		return mazo;
	}
	
}
