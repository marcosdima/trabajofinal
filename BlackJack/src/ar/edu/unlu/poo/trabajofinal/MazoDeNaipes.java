package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.enumerados.ContenidoDeCarta;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class MazoDeNaipes extends Mazo {
	
	public MazoDeNaipes() {
		
		super(52);
		this.setCartas();
		
	}

	public void setCartas() {
		
		Palo[] palos = Palo.values();
		ContenidoDeCarta[] valores = ContenidoDeCarta.values();
		Carta card;
		
		for (Palo palo : palos) {
			
			for (ContenidoDeCarta i : valores) {
			
				card = new Carta(palo, i);
				this.addCarta(card);
				
			}
			
		}
		
	}
	
}
