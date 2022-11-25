package ar.edu.unlu.poo.gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int ANCHO = 1200;
	private final int ALTO = 800;
	
	public Frame(String titulo) {
		
		super(titulo);
		this.setVisible(true);
		this.setSize(this.ANCHO, this.ALTO);
		
	}
	
	public void append(Component comp) {
		
		JPanel panel = (JPanel) this.getContentPane();
		
		panel.removeAll();
		panel.add(comp);
		panel.updateUI();
		
		
	}
	

}
