package ar.edu.unlu.poo.gui;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Frame {
	
	private JFrame frame;
	private Panel panelPrincipal;
	
	public Frame(String titulo, int ancho, int alto) {
		
		this.iniciarFrame(titulo);
		this.setSize(ancho, alto);
		this.setPanel();
		this.setClose();
		
		// Por ahora lo voy a dejar así, capaz en un futuro lo repienso.
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

	// Funciones con panelPrincipal.
	public void setLayoutPrincipal(LayoutManager lymg) {

		this.panelPrincipal.setLayout(lymg);
		
	}
	
	public void addToPrincipal(Componente comp) {

		this.panelPrincipal.add(comp);
		
	}
	
	public void addToPrincipal(Componente comp, String cardinal) {
		
		this.panelPrincipal.add(comp, cardinal);
		
	
	}
	
	public void clear() {
		
		// CONTINUAR.
		System.out.println("asdasd");
		
		/*
		Component[] componentes = this.panelPrincipal.getPanel().getComponents();
		
		for (Component c : componentes) {
			
			this.frame.remove(c);
			
		}
		*/
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
	
	public void oculto() {
		
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
