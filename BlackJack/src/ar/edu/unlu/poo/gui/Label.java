package ar.edu.unlu.poo.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {

	private static final long serialVersionUID = 1L;
	private Font fuente;

	public Label (String tag, String fuente, int size, int orden) {
		
		super(tag, orden);
		this.setFuente(fuente, size);
		
	}
	
	public Label(String tag, String fuente, int size) {
		
		this (tag, fuente, size, Label.CENTER);
		
	}
	
	public Label(String tag, int size, int orden) {
		// Si le pasas un tag, size y un orden, podes setear su alineamiento.
		this (tag, "FreeMono", size, orden);
		
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
