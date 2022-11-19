package ar.edu.unlu.poo.trabajofinal;

public enum EstadoManoBlackJack implements EstadoDeMano{
	
	BLACKJACK(21),
	MENORA21(0), 
	MAYORA21(0), 
	IGUALA21(21),
	NULO(0);
	
	private int puntos;
	
	EstadoManoBlackJack(int puntos){
		
		this.puntos = puntos;
		
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
