package ar.edu.unlu.poo.trabajofinal.commons;


public interface Observador {

	public void actualizar(IMensaje event, Object objeto);
	
	public void actualizar(IMensaje event);
	
}
