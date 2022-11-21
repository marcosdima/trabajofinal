package ar.edu.unlu.poo.gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Boton extends Componente {

	private JButton boton;
	private static int nroDeBotones;
	
	public Boton(String str) {

		this.setBoton(str);
		Boton.nroDeBotones++;
		
	}
	
	public Boton() {
		
		this ("Botón " + Boton.nroDeBotones);
		
	}

	public void setBoton(String str) {
		
		this.boton = new JButton(str);
		this.setComponent(boton);
				
	}

	public void evento(ActionListener al) {
		
		this.boton.addActionListener(al);
		
	}

	public void apagar() {
		
		this.boton.setEnabled(false);;
		
	}
	
}
