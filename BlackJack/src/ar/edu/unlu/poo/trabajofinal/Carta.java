package ar.edu.unlu.poo.trabajofinal;

public class Carta{
	
	private Palo paloDeCarta;
	private ContenidoDeCarta contenido; 
	private boolean visible;
	
	public Carta(Palo palo, ContenidoDeCarta cartita) {
		
		this.contenido = cartita;
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
		
		return this.getLabel() + " de " + this.paloDeCarta;
		
	}
	
	public ContenidoDeCarta getContenido() {
		
		return this.contenido;
		
	}
	
}
