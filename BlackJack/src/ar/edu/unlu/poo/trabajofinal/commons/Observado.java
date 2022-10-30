package ar.edu.unlu.poo.trabajofinal.commons;

import ar.edu.unlu.poo.trabajofinal.Apuesta;
import ar.edu.unlu.poo.trabajofinal.JugadorBlackJack;

public interface Observado {
	
	// Hago que devulva un booleano para saber si se notificó algo.
	
	public boolean notificar(IMensaje algo, JugadorBlackJack actualizacion);
	
	public boolean notificar(IMensaje algo, Apuesta actualizacion);
	
	public boolean notificar(IMensaje tagEvento);
	
	public void agregarObservador(Observador observer);

}
