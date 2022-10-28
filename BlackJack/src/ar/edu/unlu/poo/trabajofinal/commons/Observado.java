package ar.edu.unlu.poo.trabajofinal.commons;

import ar.edu.unlu.poo.trabajofinal.enumerados.Evento;

public interface Observado {
	
	public void notificar(Evento tagEvento, Object actualizacion);

}
