package ar.edu.unlu.poo.trabajofinal.commons;

public enum Evento implements IMensaje{

	JUGADORCARGADO("Jugador agregado!"),
	MOSTRARMANO(""),
	PRIMERAREPARTIDA(""),
	NOAPUESTA("Perfecto, proseguimos."),
	APUESTASETEADA("Apuesta seteada!"),
	JUGAR("");
	
	String label;
	
	Evento(String label) {
		
		this.label = label;
		
	}
	
	public String getDescripcion() {
		
		return this.label;
		
	}
	
}
