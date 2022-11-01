package ar.edu.unlu.poo.trabajofinal;

public class JugadorBlackJack extends Jugador{
	
	private Apuesta apuesta;
	
	public JugadorBlackJack(String nombre, int money) {
		
		super(nombre, money);
		this.setTodaviaNoJugo(true);
		this.setApuesta(new Apuesta());
		
	}

	public Apuesta getApuesta() {
		return apuesta;
	}
	
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}

}
