package ar.edu.unlu.poo.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int ANCHO = 1200;
	private final int ALTO = 800;
	private Panel panelPrincipal;
	
	public Frame(String titulo) {
		
		super(titulo);
		this.setVisible(true);
		this.setSize(this.ANCHO, this.ALTO);
		this.setPanelPrincipal();
		this.getContentPane().add(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void append(Component comp) {
		
		
		this.getContentPane().removeAll();
		this.setPanelPrincipal();
		this.getContentPane().add(panelPrincipal);
		this.panelPrincipal.add(comp, BorderLayout.CENTER);
		this.panelPrincipal.updateUI();
		
	}
	
	public void setPanelPrincipal() {
		
		this.panelPrincipal = new Panel();
		this.panelPrincipal.setLayout(new BorderLayout(50, 50));
		this.panelPrincipal.addVacio(BorderLayout.WEST);
		this.panelPrincipal.addVacio(BorderLayout.NORTH);
		this.panelPrincipal.addVacio(BorderLayout.EAST);
		this.panelPrincipal.addVacio(BorderLayout.SOUTH);
		
		
		
	}
	

}
