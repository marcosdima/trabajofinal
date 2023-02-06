package ar.edu.unlu.poo.trabajofinal.commons;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;
	private IMensaje tag;
	private Object remitente;
	
	public Mensaje(IMensaje tag, Object remitente) {
		
		this.tag = tag;
		this.remitente = remitente;
		
	}

	public IMensaje getTag() {
		return tag;
	}

	public Object getRemitente() {
		return remitente;
	}
	
	
	
}
