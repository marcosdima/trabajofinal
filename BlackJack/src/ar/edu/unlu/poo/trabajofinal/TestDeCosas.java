package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.mistads.NodoPila;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class TestDeCosas {

	public static void main(String[] args) {
		
		MazoDeNaipes mazo = new MazoDeNaipes();
		int i = 1;
		
		
		for (Carta c : mazo.getMazoDeCartas()) {
			
			System.out.println("Carta " + i + ": " + c.getNumero() + " de " + c.getPaloDeCarta());
			i++;
			
		}
		
		mazo.barajar();
		
		Carta cartinha = (Carta) mazo.getBaraja().getTope().getDato().getContenido();
		
		System.out.println(cartinha.getPaloDeCarta() + "" + cartinha.getInt());
		
	}

}
