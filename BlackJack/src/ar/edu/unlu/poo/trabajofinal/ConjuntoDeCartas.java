package ar.edu.unlu.poo.trabajofinal;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ConjuntoDeCartas implements Serializable {
	
	private static final long serialVersionUID = 4848483516597270146L;
	private ArrayList<Carta> cartas;
	private int tam;
	
	public ConjuntoDeCartas(int tam) {

		this.setTam(tam);
		this.cartas = new ArrayList<Carta>(tam);
		
	}
	
	public void addCarta(Carta carta) {
		
		this.cartas.add(carta);
		
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
	
	protected void clear() {
		
		this.cartas.clear();

	}

	public boolean contains(ContenidoDeCarta carta) {
		
		boolean res = false;
		
		for (Carta cartita : this.cartas) {
			
			if (cartita.getLabel() == carta.getLabel()) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}
	
}
