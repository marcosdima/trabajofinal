package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.misfunciones.Rand;
import ar.edu.unlu.poo.mistads.Pila;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class MazoDeNaipes extends Mazo {
	
	public Rand random = new Rand();
	
	public MazoDeNaipes() {
		
		super(52);
		
	}

	public void setCartas() {
		
		Palo[] palos = {Palo.CORAZON, Palo.DIAMANTE, Palo.PICA, Palo.TREBOL};
		Carta card;
		Rand azar = new Rand();
		
		for (Palo palo : palos) {
		
			int[] numerosRandom = azar.randomList(13);
			
			for (int i : numerosRandom) {
			
				card = new Carta(palo, i);
				this.addCarta(card);
				
			}
			
		}
		
	}

	public void barajar() {
		
		int tamanio = this.getNumeroDeCartas();
		int[] lista = random.randomList(tamanio);
		Pila contenedor = new Pila(this.getNumeroDeCartas());
		
		
		for (int numero : lista) {
			
			contenedor.apilar(this.getMazoDeCartas().get(numero - 1));
			
		}
		
		this.setBaraja(contenedor);
	
	}
	
}
