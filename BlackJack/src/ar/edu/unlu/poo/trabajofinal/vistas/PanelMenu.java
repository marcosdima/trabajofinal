package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.Component;
import javax.swing.JPanel;


public abstract class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private Component[] cosas;
	private int espacioAlto;
	private int espacioLados;
	
	public PanelMenu(Component[] cosas, int espacioAlto, int espacioLados) {
		
		super();
		this.cosas = cosas;
		this.setEspacioAlto(espacioAlto);
		this.setEspacioLados(espacioLados);
		
	}

	public PanelMenu(Component[] botones) {
		
		this (botones, 0 ,0);
		
	}
	
	public Component getComponente(int tag) {
		
		Component componentazo = this.cosas[tag];
		
		return componentazo;
		
	}

	public Component[] getComponentes() {
		return cosas;
	}

	protected abstract void setFormato();

	public int getEspacioAlto() {
		return espacioAlto;
	}

	public void setEspacioAlto(int espacioAlto) {
		this.espacioAlto = espacioAlto;
	}

	public int getEspacioLados() {
		return espacioLados;
	}

	public void setEspacioLados(int espacioLados) {
		this.espacioLados = espacioLados;
	}

	
}
