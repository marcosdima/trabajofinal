package ar.edu.unlu.poo.gui;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Frame {
	
	protected JFrame frame;
	protected Panel panelPrincipal;
	
	public Frame(String titulo, int ancho, int alto) {
		
		this.iniciarFrame(titulo);
		this.setSize(ancho, alto);
		this.setPanel();
		this.setClose();
		
		// Por ahora lo voy a dejar así, capaz en un futuro lo repiesno.
		this.frame.setResizable(false);
		
	}
	
	public Frame(String titulo) {
		
		this (titulo, 800, 600);
		
	}
	
	public Frame() {
		
		this ("");
		
	}

	private void iniciarFrame(String titulo) {
		
		this.frame = new JFrame(titulo);
		
	};

	public void add(Component comp) {
		
		this.frame.add(comp);
		
	}
	
	// Funciones con panelPrincipal.
	
	public void setLayoutPrincipal(LayoutManager lymg) {

		this.panelPrincipal.setLayout(lymg);
		
	}
	
	public void addToPrincipal(Componente comp) {
		
		this.panelPrincipal.add(comp);
		
	}
	
	// Getters and Setters
	
	private void setPanel() {
		
		Panel p = new Panel(this.frame.getContentPane());
		this.panelPrincipal = p;
		
	}

	private void setVisibilidad(boolean esVisible) {
		
		this.frame.setVisible(esVisible);
		
	}
	

	public void setSize(int ancho, int alto) {
		
		this.frame.setSize(ancho, alto);
		
	}
	
	public void visible() {
		
		this.setVisibilidad(true);
		
	}
	
	
	public void oculta() {
		
		this.setVisibilidad(false);
		
	}

	
	
	public int getAncho() {
		return this.frame.getWidth();
	}

	

	public int getAlto() {
		return this.frame.getHeight();
	}

	public Panel getPanel() {
		
		return this.panelPrincipal;
		
	}

	public void setClose(int nro) {
		
		this.frame.setDefaultCloseOperation(nro);
		
	}
	
	public void setClose() {
		
		int set = WindowConstants.EXIT_ON_CLOSE;
		this.setClose(set);
		
	}

}
