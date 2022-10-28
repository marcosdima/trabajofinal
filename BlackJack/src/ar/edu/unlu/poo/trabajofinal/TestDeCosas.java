package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.enumerados.ContenidoDeCarta;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class TestDeCosas {

	public static void main(String[] args) {
		
		MazoDeNaipes mazo = new MazoDeNaipes();		
		Mano test = new Mano();
		
		test.addCarta(new Carta(Palo.CORAZON,ContenidoDeCarta.CINCO));
		test.addCarta(new Carta(Palo.TREBOL,ContenidoDeCarta.AS));
		
		System.out.println(test.getPuntaje());
		
		/*
		for (Carta c : mazo.getCartas()) {
			
			System.out.println(c.getClaveDeCarta());
			
		}
		*/
		
	}

}
