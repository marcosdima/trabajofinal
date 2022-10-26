package ar.edu.unlu.poo.trabajofinal;

public class TestDeCosas {

	public static void main(String[] args) {
		
		MazoDeNaipes mazo = new MazoDeNaipes();
		int i = 1;
		
		/*
		for (Carta c : mazo.getConjuntoDeCartas()) {
			
			System.out.println("Carta " + i + ": " + c.getNumero() + " de " + c.getPaloDeCarta());
			i++;
			
		}
		*/
		
		mazo.barajar();
		System.out.println(mazo.getBaraja().getTope().getNumero() + " de " + mazo.getBaraja().getTope().getPaloDeCarta());
		System.out.println(mazo.getBaraja().getTope().getNumero() + " de " + mazo.getBaraja().getTope().getPaloDeCarta());
		mazo.agarrarCarta();
		System.out.println(mazo.getBaraja().getTope().getNumero() + " de " + mazo.getBaraja().getTope().getPaloDeCarta());
		System.out.println(mazo.getBaraja().getTope().getNumero() + " de " + mazo.getBaraja().getTope().getPaloDeCarta());
	}

}
