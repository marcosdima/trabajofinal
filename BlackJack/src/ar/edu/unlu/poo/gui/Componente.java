package ar.edu.unlu.poo.gui;

import java.awt.Component;
import java.awt.event.ActionListener;

public abstract class Componente {

	Component componente;
		
	public Component getComponent() {
		
		return this.componente;
		
	}
	
	protected void setComponent(Component componente) {
		
		this.componente = componente;
		
	}
	
	public void visible() {
		
		this.componente.setVisible(true);
		
	}
	
	public void oculto() {
		
		this.componente.setVisible(false);
		
	}
	
	public void apagar() {
		
		this.componente.setEnabled(true);
		
	}
	
	public void prender() {
		
		this.componente.setEnabled(true);
		
	}
	
	public abstract String getText();

	public abstract void evento(ActionListener action);

}
