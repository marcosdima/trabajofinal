package ar.edu.unlu.poo.trabajofinal;

import java.io.Serializable;

public class Apuesta implements Serializable {
	
	private static final long serialVersionUID = 1L;
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

	public int getGanancia(boolean blackjack) {
		
		int monto;
		
		if (blackjack) {
			
			monto = (this.monto) + this.getMonto();
			
		}
		else {
			
			monto = (this.monto / 2) + this.getMonto();
			
		}
		
		return monto;
		
	}
	
}
