package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.mistads.Dato;
import ar.edu.unlu.poo.trabajofinal.enumerados.Palo;

public class Carta implements Dato {
	
	private Palo paloDeCarta;
	private int numero;
	
	public Carta(Palo palo, int nro) {
		
		this.setNumero(nro);
		this.setPaloDeCarta(palo);
		
	}

	public Palo getPaloDeCarta() {
		return paloDeCarta;
	}

	private void setPaloDeCarta(Palo paloDeCarta) {
		this.paloDeCarta = paloDeCarta;
	}

	public int getNumero() {
		return numero;
	}

	private void setNumero(int numero) {
		this.numero = numero;
	}

	
	public String getClave() {
		
		String clave = this.getPaloDeCarta().toString();
		
		return clave;
	}


	@Override
	public int getInt() {
		
		return this.getNumero();
	}


	public Object getContenido() {
		return this;
	}

	
	
}
