package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public interface Observado {
	
	// Hago que devulva un booleano para saber si se notificó algo.
	
	public boolean notificar(Evento mensaje, IJugador data);
	
	public boolean notificar(Evento mensaje, ArrayList<IJugador> actuDatos);
	
	public boolean notificar(Evento tagEvento);
	
	public boolean notificar(SaltoError mensaje, IJugador data);
	
	public boolean notificar(Notificacion mensaje, IJugador data);
	
	public void agregarObservador(Observador observer);

}
