package ar.edu.unlu.poo.trabajofinal;

public class Carta{
	
	private Palo paloDeCarta;
	private ContenidoDeCarta contenido; 
	private boolean visible;
	
	public Carta(Palo palo, ContenidoDeCarta cartita) {
		
		/*
		 * Constructor de la clase 'Carta'.
		 * Recibe como parámtro un enumerado 'Palo' y un enumerado 'ContenidoDeCarta'.
		 * 
		 * */
		
		// Seteo de variables.
		this.setContenido(cartita);
		this.setPaloDeCarta(palo);
		this.setVisibilidad(false);
		
	}

	public Palo getPaloDeCarta() {
		return paloDeCarta;
	}

	private void setPaloDeCarta(Palo paloDeCarta) {
		this.paloDeCarta = paloDeCarta;
	}

	public int getValor() {
		return this.contenido.getValor();
	}
	
	public void setVisibilidad(boolean esVisible) {
		
		this.visible = esVisible;
		
	}
	
	public boolean esVisible() {
		
		return this.visible;
		
	}

	public String getLabel() {
		
		return this.contenido.getLabel();
		
	}
	
	public String getDesc() {
		
		/* Seteo la variable desc para que devuevla el formato:
		 * 'Label' de 'Palo'
		 * */
		
		String desc = this.getLabel() + " de " + this.paloDeCarta;
		
		return desc;
		
	}
	
	public ContenidoDeCarta getContenido() {
		
		return this.contenido;
		
	}

	public void setContenido(ContenidoDeCarta contenido) {
		this.contenido = contenido;
	}
	
	
	
}
