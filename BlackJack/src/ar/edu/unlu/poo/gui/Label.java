package ar.edu.unlu.poo.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {

	private static final long serialVersionUID = 1L;
	private Font fuente;

	public Label(String tag, String fuente, int size) {
		
		super(tag);
		this.setFuente(fuente, size);
		
	}
	
	public Label(String tag) {
		
		this (tag, "FreeMono", 50);
		
	}
	
	public Label() {
		
		this ("", "", 50);
		
	}
	
	public void setFuente(String fuente, int size) {
		
		this.fuente = new Font(fuente, Font.ITALIC, size);
		this.setFont(this.fuente);
		
	}

	public void setSizeFont(int size) {
		
		this.setFuente(this.fuente.getName(), size);
		
	}
	
}
