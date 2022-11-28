package ar.edu.unlu.poo.misfunciones;

public class Intencion {
	
	private String[] out = {"salir", "exit", "slir", "salida", "ecsit"};
	private String[] positive = {"si", "s", "yes", "y", "sisi", "sis", "yyes"};
	private String[] negative = {"no", "n", "nop", "nope", "nono", "nain", "nel"};
	private String[] quit = {"renuncio", "me retiro", "no juego más", "no juego mas", "quit", "me rindo", "rindo", "rendir"};
	// Truco pa la plata
	private String[] esoyam = {"esoyam", "dame plata", "platita", "biyuya", "plata", "una monedita por favor"};
	
	private boolean recorrer(String input, String[] array) {
		
		boolean res = false;
		input = input.toLowerCase();
				
		for (String str : array) {
			
			if (str.equals(input)) {
				
				res = true;
				
			}
			
		}	
		
		return res;
		
	}
	
	public boolean out(String input) {
	
		return this.recorrer(input, this.out);
	
	}
	
	public boolean positive(String input) {
		
		return this.recorrer(input, this.positive);
	
	}
	
	public boolean negative(String input) {
		
		return this.recorrer(input, this.negative);
	
	}
	
	public boolean quit(String input) {
		
		return this.recorrer(input, this.quit);
		
	}
	
	public boolean esoyam(String input) {
		
		return this.recorrer(input, this.esoyam);
		
	}

}
