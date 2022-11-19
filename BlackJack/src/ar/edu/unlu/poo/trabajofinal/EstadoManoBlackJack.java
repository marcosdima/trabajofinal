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

	@Override
	public Comparativo esMejorQue(EstadoDeMano otraMano) {

		Comparativo res = Comparativo.PEOR;
		boolean esPeor;
		
		switch (this) {
		
			case BLACKJACK:
				
				if (otraMano != EstadoManoBlackJack.BLACKJACK) {
					
					res = Comparativo.MEJOR;
					
				}
				else {
					
					res = Comparativo.IGUAL;
					
				}
				
			case MENORA21:
				
				esPeor = (otraMano == EstadoManoBlackJack.BLACKJACK) || (otraMano.getPuntos() > this.getPuntos());
				
				if (esPeor) {
					
					res = Comparativo.PEOR;
					
				}
				else if (otraMano.getPuntos() == this.getPuntos()) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.MEJOR;
					
				}
				
			case MAYORA21:
				
				if (otraMano == EstadoManoBlackJack.MAYORA21) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.PEOR;
					
				}
				
			case IGUALA21:
				
				esPeor = (otraMano == EstadoManoBlackJack.BLACKJACK);
				
				if (esPeor) {
					
					res = Comparativo.PEOR;
					
				}
				else if (otraMano == EstadoManoBlackJack.IGUALA21) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.MEJOR;
					
				}
				
				
		default:
			break;
		
		}
		
		return res;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	
}
