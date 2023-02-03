package ar.edu.unlu.poo.trabajofinal.commons;

public class Mensaje {

	private IMensaje tag;
	private String remitente;
	
	public Mensaje(IMensaje tag, String remitente) {
		
		this.tag = tag;
		this.remitente = remitente;
		
	}

	public IMensaje getTag() {
		return tag;
	}

	public String getRemitente() {
		return remitente;
	}
	
	
	
}
