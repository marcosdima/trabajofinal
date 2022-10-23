package ar.edu.unlu.poo.mistads;

public class NodoPila {
	
	private Dato dato;
	private NodoPila anterior;
	
	public NodoPila(Dato dato, NodoPila anterior) {
		
		this.setDato(dato);
		this.setAnterior(anterior);
		
	}
	
	public NodoPila(Dato dato) {
		
		this (dato, null);
	
	}
	
	public NodoPila() {
		
		this (null, null);
		
	}

	public Dato getDato() {
		return dato;
	}

	public void setDato(Dato dato) {
		this.dato = dato;
	}

	public NodoPila getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoPila anterior) {
		this.anterior = anterior;
	}
	
	public boolean esNulo() {
		
		boolean res = false;
		
		if (this.dato == null) {
			
			res = true;
			
		}
		
		return res;
		
	}
	
	public boolean esInicial() {
		
		boolean res = false;
		
		if (this.anterior == null) {
			
			res = true;
			
		}
		
		return res;
		
	}
	
}
