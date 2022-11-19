package ar.edu.unlu.poo.trabajofinal.commons;

public enum Evento implements IMensaje{

	JUGADORCARGADO("Jugador agregado!"),
	MOSTRARMANO(""),
	PRIMERAREPARTIDA(""),
	NOAPUESTA("Perfecto, proseguimos."),
	APUESTASETEADA("Apuesta seteada!"),
	TERMINOLAMANO("Se terminó la mano!"),
	PREGUNTAROTRA("Querés otra carta?"),
	PRIMERAPUESTA(""),
	BLACKJACK("Que suerte, tenés BlackJack!"),
	TERMINOTURNO("Tu turno terminó!"),
	FINDEMANO("La Mano se terminó! Sigamos nomás..."),
	JUGAR("");
	
	String label;
	
	Evento(String label) {
		
		this.label = label;
		
	}
	
	public String getDescripcion() {
		
		String res = this.label;
		return res;
		
	}
	
}
