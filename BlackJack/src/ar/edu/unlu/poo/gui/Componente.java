package ar.edu.unlu.poo.gui;

import java.awt.Component;

public abstract class Componente {

	Component componente;
	
	public Componente(Component componente) {
		
		this.setComponent(componente);
		
	}
	
	public Component getComponent() {
		
		return this.componente;
		
	}
	
	protected void setComponent(Component componente) {
		
		this.componente = componente;
		
	}
	
}
