package ar.edu.unlu.poo.trabajofinal;

public class Apuesta {
	
	private int monto;
	
	public Apuesta(int monto) {
		
		this.setApuesta(monto);
		
	}
	
	public Apuesta() {
		
		this (0);
		
	}
	
	public int getMonto() {
		return monto;
	}

	public void setApuesta(int apuesta) {
		this.monto = apuesta;
	}

	
	
}
