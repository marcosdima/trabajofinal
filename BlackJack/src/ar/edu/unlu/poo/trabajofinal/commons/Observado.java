package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public interface Observado {
	
	// Hago que devulva un booleano para saber si se notificó algo.
	
	public boolean notificar(IMensaje mensaje, IJugador data);
	
	public boolean notificar(IMensaje mensaje, ArrayList<IJugador> actuDatos);
	
	public boolean notificar(IMensaje tagEvento);
	
	public void agregarObservador(Observador observer);

}
