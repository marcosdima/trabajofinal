package ar.edu.unlu.poo.trabajofinal.commons;

public enum SaltoError implements IMensaje {

	ERRORMAXJUGADORES("Se alcanzó el máximo de jugadores posibles."),
	ERRORAPUESTA("La apuesta no iguala o supera la apuesta mínima."),
	NOHAYJUGADORESCARGADOS("No se puede jugar sin ningun jugador cargado. Por favor, cargue al menos un jugador."),
	ERRORFALTADEDINERO("El jugador no tiene el dinero suficiente para realizar dicha apuesta."),
	APOSTONONUMERO("La apuesta tiene que set un número igual o mayor a 0! Si no querés apostar, ingresa cero.");
	
	private String descripcion;
	
	SaltoError(String error) {
		
		this.descripcion = "Se detectó el siguiente error: " + '\r' + '\n' + this + ": " + error;
		
	}

	
	public String getDescripcion() {
		return this.descripcion;
	}
	
}
