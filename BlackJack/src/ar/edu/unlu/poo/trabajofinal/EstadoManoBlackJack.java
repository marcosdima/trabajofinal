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
	public boolean esMejorQue(EstadoDeMano otraMano) {

		boolean res = false;
		
		if (this.getPuntos() > otraMano.getPuntos()) {
			
			res = true;
			
		}
		else if ( (this.getPuntos() == otraMano.getPuntos() ) && ( this.equals(EstadoManoBlackJack.BLACKJACK) ) && (!otraMano.equals(EstadoManoBlackJack.BLACKJACK))) {
			
			res = true;
			
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
