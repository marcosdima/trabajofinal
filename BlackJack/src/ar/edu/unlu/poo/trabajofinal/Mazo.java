package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.misfunciones.Rand;
import ar.edu.unlu.poo.mistads.Pila;

public abstract class Mazo extends ConjuntoDeCartas {
	
	private Pila<Carta> baraja;
	public Rand random;
	
	public Mazo(int tam) {

		super(tam);
		this.random = new Rand();
	
	}
	
	public void barajar() {
		
		int tamanio = this.getTam();
		int[] lista = random.randomList(tamanio);
		Pila<Carta> contenedor = new Pila<Carta>(tamanio);
		
		
		for (int numero : lista) {
			
			contenedor.apilar(this.getCartas().get(numero - 1));
			
		}
		
		this.setBaraja(contenedor);
	
	};

	public Pila<Carta> getBaraja() {
		return baraja;
	}

	public void setBaraja(Pila<Carta> baraja) {
		this.baraja = baraja;
	}
	
	// Queda pendiente pensar en la medida control. Si mezclamos cada vez que termina la partida no hace falta.
	public Carta agarrarCarta() {
		
		Carta res = this.baraja.getTope();
		this.baraja.desapilar();
		return res;
		
	}

}
