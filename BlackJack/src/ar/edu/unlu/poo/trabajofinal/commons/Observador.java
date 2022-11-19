package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public interface Observador {
	
	public void actualizar(Evento event, ArrayList<IJugador> objeto);
	
	public void actualizar(Evento event, IJugador objeto);
	
	public void actualizar(SaltoError event, IJugador objeto);

	public void actualizar(Evento event);
	
}
