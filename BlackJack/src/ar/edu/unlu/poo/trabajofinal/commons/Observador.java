package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.DatosDeJugador;
import ar.edu.unlu.poo.trabajofinal.JugadorBlackJack;

public interface Observador {

	public void actualizar(IMensaje event, Object objeto);
	
	public void actualizar(IMensaje event, ArrayList<DatosDeJugador> objeto);
	
	public void actualizar(IMensaje event, JugadorBlackJack objeto);
	
	public void actualizar(IMensaje event);
	
}
