package ar.edu.unlu.poo.trabajofinal;

public enum Comparativo {
	MEJOR, PEOR, IGUAL;
	
	public boolean gana() {
		
		return (this == MEJOR);
		
		
	}
	
	public boolean empate() {
		
		return (this == IGUAL);
		
		
	}
	
	public boolean pierde() {
		
		return (this == PEOR);

	}
	
	
}
