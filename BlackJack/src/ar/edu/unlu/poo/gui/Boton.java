package ar.edu.unlu.poo.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class Boton extends JButton {

	private static final long serialVersionUID = 1L;

	public Boton(String tag) {
		
		super(tag);
		this.setFuente("FreeMono");
		this.setBack(170, 183, 184);
		
	}
	
	public void setFuente(String fuente) {
		
		Font fuenteCustom = new Font(fuente, Font.BOLD, this.getFont().getSize() + 10);
		this.setFont(fuenteCustom);
		
	};
	
	public void setBack(int r, int g, int b) {
		
		Color color = new Color(r, g, b);
		this.setBackground(color);
		
	}
	
}
