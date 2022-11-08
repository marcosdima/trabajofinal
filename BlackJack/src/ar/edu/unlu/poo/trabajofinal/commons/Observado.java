package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.DatosDeJugador;

public interface Observado {
	
	// Hago que devulva un booleano para saber si se notificó algo.
	
	public boolean notificar(IMensaje mensaje, DatosDeJugador data);
	
	public boolean notificar(IMensaje mensaje, ArrayList<DatosDeJugador> actuDatos);
	
	public boolean notificar(IMensaje tagEvento);
	
	public void agregarObservador(Observador observer);

}
