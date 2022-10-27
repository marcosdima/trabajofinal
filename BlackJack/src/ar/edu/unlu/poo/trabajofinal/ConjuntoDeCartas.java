package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

public abstract class ConjuntoDeCartas {
	
	private ArrayList<Carta> cartas;
	private int tam;
	
	public ConjuntoDeCartas(int tam) {

		this.setTam(tam);
		this.cartas = new ArrayList<Carta>(tam);
		
	}
	
	public void addCarta(Carta carta) {
		
		this.cartas.add(carta);
		
	}
	
	public abstract void setCartas();

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
	
	

}
