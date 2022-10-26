package ar.edu.unlu.poo.mistads;

public class NodoPila<Cosa> {
	
	private Cosa elemento;
	private NodoPila<Cosa> anterior;
	
	public NodoPila(Cosa elemento, NodoPila<Cosa> anterior) {
		
		this.setCosa(elemento);
		this.setAnterior(anterior);
		
	}
	
	public NodoPila(Cosa dato) {
		
		this (dato, null);
	
	}
	
	public NodoPila() {
		
		this (null, null);
		
	}

	public Cosa getElemento() {
		return elemento;
	}

	public void setCosa(Cosa dato) {
		this.elemento = dato;
	}

	public NodoPila<Cosa> getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoPila<Cosa> anterior) {
		this.anterior = anterior;
	}
	
	public boolean esNulo() {
		
		boolean res = false;
		
		if (this.elemento == null) {
			
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
