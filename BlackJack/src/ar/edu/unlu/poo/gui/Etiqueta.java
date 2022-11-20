package ar.edu.unlu.poo.gui;

import javax.swing.JLabel;

public class Etiqueta extends Componente {

	protected JLabel tag;
	protected static int nroDeLabels;
	
	public Etiqueta(String str) {
		
		super(null);
		this.tag = new JLabel();
		this.setTag(str);
		this.setComponent(this.tag);
		Etiqueta.nroDeLabels++;
		
	}

	public Etiqueta() {
		
		this ("Etiqueta " + Etiqueta.nroDeLabels);
		
	}
	
	public JLabel getLabel() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag.setText(tag);
	}
	
	public String getString() {
		
		return this.tag.toString();
		
	}
	
}
