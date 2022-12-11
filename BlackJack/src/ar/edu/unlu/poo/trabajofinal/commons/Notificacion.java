package ar.edu.unlu.poo.trabajofinal.commons;

public enum Notificacion implements IMensaje {
	
	JUGADORCARGADO("Jugador agregado!"),
	NOAPUESTA("Perfecto, proseguimos."),
	APUESTASETEADA("Apuesta seteada!"),
	BLACKJACK("Que suerte, tenés BlackJack!");
	
	private String label;
	private String remitente = "unknown";
	
	Notificacion(String label) {
		
		this.label = label;
		
	}
	
	public String getDescripcion() {
		
		String res = this.label;
		return res;
		
	}
	
	public void setRemitente(String remitente) {
		
		this.remitente = remitente;
		
	}

	
	@Override
	public String getRemitente() {
		
		return this.remitente;
		
	}

	
	
}
