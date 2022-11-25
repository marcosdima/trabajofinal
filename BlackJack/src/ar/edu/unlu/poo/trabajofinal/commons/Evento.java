package ar.edu.unlu.poo.trabajofinal.commons;

public enum Evento implements IMensaje{

	MOSTRARMANO(""),
	PRIMERAREPARTIDA(""),
	PREGUNTAROTRA("Querés otra carta?"),
	PRIMERAPUESTA(""),
	TERMINOTURNO("Tu turno terminó!"),
	FINDEMANO("La Mano se terminó! Sigamos nomás..."),
	JUGAR(""),
	SINPLATA("Te quedaste sin plata!"),
	FINDELJUEGO("Ya no hay personas en juego! " + '\n' +  "El juego se terminó." + '\n' + "Saludos!");
	
	String label;
	
	Evento(String label) {
		
		this.label = label;
		
	}
	
	public String getDescripcion() {
		
		String res = this.label;
		return res;
		
	}
	
}
