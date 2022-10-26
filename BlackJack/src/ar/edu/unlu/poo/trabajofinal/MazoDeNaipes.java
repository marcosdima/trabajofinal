package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class MazoDeNaipes extends Mazo {
	
	public MazoDeNaipes() {
		
		super(52);
		
	}

	public void setCartas() {
		
		Palo[] palos = {Palo.CORAZON, Palo.DIAMANTE, Palo.PICA, Palo.TREBOL};
		Carta card;
		
		for (Palo palo : palos) {
		
			int[] numerosRandom = this.random.randomList(13);
			
			for (int i : numerosRandom) {
			
				card = new Carta(palo, i);
				this.addCarta(card);
				
			}
			
		}
		
	}
	
}
