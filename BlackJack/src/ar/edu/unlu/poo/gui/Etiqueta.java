package ar.edu.unlu.poo.gui;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Etiqueta extends Componente {

	protected JLabel tag;
	protected static int nroDeLabels;
	
	public Etiqueta(String str) {
		
		this.tag = new JLabel();
		this.setTag(str);
		this.setComponent(this.tag);
		Etiqueta.nroDeLabels++;
		
	}

	public Etiqueta(int str) {
		
		this (String.valueOf(str));
		
	}
	
	public Etiqueta() {
		
		this ("Etiqueta " + Etiqueta.nroDeLabels);
		
	}
	
	public static Etiqueta vacio(int espacio) {
		
		String empty = "";
		Etiqueta res;
		
		for (int i = 0; i < espacio; i++) {
			
			empty += " ";
			
		}
		
		res = new Etiqueta(empty);
		
		return res;
		
	}
	
	public static Etiqueta vacio() {
		
		return Etiqueta.vacio(5);
		
	}
	
	public JLabel getLabel() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = new JLabel(tag);
		this.setComponent(this.tag);
	}
	
	public String getText() {
		
		return this.tag.toString();
		
	}

	@Override
	public void evento(ActionListener action) {
		// TODO Auto-generated method stub
		
	}

}
