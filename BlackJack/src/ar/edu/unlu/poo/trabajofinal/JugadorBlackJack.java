package ar.edu.unlu.poo.trabajofinal;

public class JugadorBlackJack extends Jugador{
	
	private Apuesta apuesta;
	private boolean todaviaNoAposto;
	
	public JugadorBlackJack(String nombre, int money) {
		
		super(nombre, money);
		this.setTodaviaNoJugo(true);
		this.setApuesta(new Apuesta());
		this.setTodaviaNoAposto(true);
		
	}

	public Apuesta getApuesta() {
		return apuesta;
	}
	
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}
	
	public void setTodaviaNoAposto(boolean estado) {
		
		this.todaviaNoAposto = estado;
		
	}
	
	public boolean noAposto() {
		
		return this.todaviaNoAposto;
		
	}

}
