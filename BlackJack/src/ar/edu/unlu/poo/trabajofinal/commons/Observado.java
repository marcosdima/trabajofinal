package ar.edu.unlu.poo.trabajofinal.commons;


public interface Observado {
	
	public void notificar(Evento tagEvento, Object actualizacion);
	
	public void agregarObservador(Observador observer);

}
