package ar.edu.unlu.poo.trabajofinal;

public class TestDeCosas {

	public static void main(String[] args) {
		
		MazoDeNaipes mazo = new MazoDeNaipes();
		int i = 1;
		
		
		for (Carta c : mazo.getMazoDeCartas()) {
			
			System.out.println("Carta " + i + ": " + c.getNumero() + " de " + c.getPaloDeCarta());
			i++;
			
		}
		
	}

}
