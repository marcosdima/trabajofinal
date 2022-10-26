package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;
import ar.edu.unlu.poo.mistads.Pila;

public abstract class Mazo {
	
	private int numeroDeCartas;
	private ArrayList<Carta> conjuntoDeCartas;
	private Pila<Carta> baraja;
	
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
		
		if (this.conjuntoDeCartas == null) {
			this.conjuntoDeCartas = new ArrayList<Carta>(this.numeroDeCartas);
		}
		
		this.conjuntoDeCartas.add(cartita);
		
	};

	public ArrayList<Carta> getConjuntoDeCartas() {
		return conjuntoDeCartas;
	}

	public Pila<Carta> getBaraja() {
		return baraja;
	}

	public void setBaraja(Pila<Carta> baraja) {
		this.baraja = baraja;
	}

	public int getNumeroDeCartas() {
		return numeroDeCartas;
	}
	
	

}
