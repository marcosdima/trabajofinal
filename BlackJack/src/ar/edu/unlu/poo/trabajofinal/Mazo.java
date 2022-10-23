package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;
import ar.edu.unlu.poo.mistads.Pila;

public abstract class Mazo {
	
	private int numeroDeCartas;
	private ArrayList<Carta> mazoDeCartas;
	private Pila baraja;
	
	public Mazo(int tam) {

		super();
		this.setNumeroDeCartas(tam);
		this.setCartas();;
		
	}

	public void setNumeroDeCartas(int numeroDeCartas) {
		this.numeroDeCartas = numeroDeCartas;
	}
	
	public abstract void setCartas();
	
	public abstract void barajar();
	
	protected void addCarta(Carta cartita) {
		
		if (this.mazoDeCartas == null) {
			this.mazoDeCartas = new ArrayList<Carta>(this.numeroDeCartas);
		}
		
		this.mazoDeCartas.add(cartita);
		
	};

	public ArrayList<Carta> getMazoDeCartas() {
		return mazoDeCartas;
	}

	public Pila getBaraja() {
		return baraja;
	}

	public void setBaraja(Pila baraja) {
		this.baraja = baraja;
	}

	public int getNumeroDeCartas() {
		return numeroDeCartas;
	}
	
	

}
