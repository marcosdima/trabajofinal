package ar.edu.unlu.poo.trabajofinal;

public class JugadorBlackJack extends Jugador{
	
	private Apuesta apuesta;
	private boolean todaviaNoAposto;
	private boolean perdioLaMano;
	
	public JugadorBlackJack(String nombre, int money) {
		
		super(nombre, money);
		this.setTodaviaNoJugo(true);
		this.setApuesta(new Apuesta());
		this.noAposto();
		this.noPerdioLaMano();
		
	}

	private void noPerdioLaMano() {
		
		this.perdioLaMano = false;
		
	}

	public void perdioLaMano() {
		
		this.perdioLaMano = true;
		
	}
	
	public Apuesta getApuesta() {
		return apuesta;
	}
	
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}
	
	public void aposto() {
		
		this.todaviaNoAposto = false;
		
	}
	
	public void noAposto() {
		
		this.todaviaNoAposto = true;
		
	}

	public boolean estadoMano() {
		
		return this.perdioLaMano;
		
	}
	
	public boolean todaviaNoAposto() {
		
		return this.todaviaNoAposto;
		
	}
	
}
