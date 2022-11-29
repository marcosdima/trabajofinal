package ar.edu.unlu.poo.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Boton extends JButton {

	private static final long serialVersionUID = 1L;

	public Boton(String tag) {
		
		super(tag);
		this.setFuente();
		this.setBack();
		
	}
	
	public void setFuente() {
		
		Font fuente = new Font("FreeMono", Font.BOLD, this.getFont().getSize() + 10);
		this.setFont(fuente);
		
	};
	
	public void setBack() {
		
		Color color = new Color(170, 183, 184);
		this.setBackground(color);
		
	}
	
	
}
