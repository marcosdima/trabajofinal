package ar.edu.unlu.poo.trabajofinal;

public class JugadorBlackJack extends Jugador{
	
	private Apuesta apuesta;
	private boolean estaJugando;
	
	public JugadorBlackJack(String nombre, int money) {
		
		super(nombre, money);
		this.estaJugando = true;
		this.setApuesta(new Apuesta());
		
	}

	public Apuesta getApuesta() {
		return apuesta;
	}
	
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}

	public void yaPerdio() {
		
		this.estaJugando = false;
		
	}
	
	public boolean getEstado() {
		
		return this.estaJugando;
		
	}
	
}
