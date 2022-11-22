package ar.edu.unlu.poo.trabajofinal.vistas;

import java.util.ArrayList;

import ar.edu.unlu.poo.gui.Componente;
import ar.edu.unlu.poo.gui.Panel;

public abstract class PanelMenu extends Panel {

	private ArrayList<Componente> botones;
	private String[] botonTags;
	private int espacioAlto;
	private int espacioLados;
	
	public PanelMenu(Componente[] botones, int espacioAlto, int espacioLados) {
		
		super();
		this.botones = new ArrayList<Componente>();
		this.setBotonTags(botones);
		this.setEspacioAlto(espacioAlto);
		this.setEspacioLados(espacioLados);
		
	}

	public PanelMenu(Componente[] botones) {
		
		this (botones, 0 ,0);
		
	}
	
	public Componente getComponente(String tag) {
		
		boolean flag = false;
		int contador = 0;
		int index = 0;
		Componente componentazo = null;
		
		for (String etiqueta : botonTags) {
			
			if ((etiqueta.equals(tag)) && (!flag)) {
				
				flag = true;
				index = contador;
				
			}
			
			contador++;
			
		}
		
		if (flag) {
			
			componentazo = this.botones.get(index);
			
		}
		
		return componentazo;
		
	}

	public ArrayList<Componente> getComponentes() {
		return botones;
	}

	public String[] getBotonTags() {
		return botonTags;
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
	
	private void setBotonTags(Componente[] botones) {
		
		this.botonTags = new String[botones.length];
		int contador = 0;
		
		for (Componente comp : botones) {
			
			this.botonTags[contador] = comp.getText();
			contador++;
			
		}
		
	}
	
}
