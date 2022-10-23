package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.misfunciones.Rand;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class MazoDeNaipes extends Mazo {
	
	public MazoDeNaipes() {
		
		super(52);
		
	}

	public void setCartas() {
		
		int tam = this.getNumeroDeCartas();
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
		// TODO Auto-generated method stub
		
	}
	
}