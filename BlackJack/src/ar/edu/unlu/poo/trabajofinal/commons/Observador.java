package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public interface Observador {

	public void actualizar(IMensaje event, Object objeto);
	
	public void actualizar(IMensaje event, ArrayList<IJugador> objeto);
	
	public void actualizar(IMensaje event, IJugador objeto);

	public void actualizar(IMensaje event);
	
}
