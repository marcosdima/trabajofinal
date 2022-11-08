package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.DatosDeJugador;

public interface Observador {

	public void actualizar(IMensaje event, Object objeto);
	
	public void actualizar(IMensaje event, ArrayList<DatosDeJugador> objeto);
	
	public void actualizar(IMensaje event, DatosDeJugador objeto);

	public void actualizar(IMensaje event);
	
}
