package ar.edu.unlu.poo.trabajofinal;

public enum EstadoMano {
	BLACKJACK, 
	MENORA21, 
	MAYORA21, 
	IGUALA21,
	NULO;
	
	public boolean sigue() {
		
		 boolean sigue = false;
		
		if (this == EstadoMano.MENORA21) {
			
			sigue = true;
			
		}
		
		return sigue;
		
	}
	
}
