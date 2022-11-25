package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenuPrincipal extends PanelMenu {
	
	public PanelMenuPrincipal(Component[] botones, int alto, int lados) {
		
		super(botones, alto, lados);
		this.setFormato();
		
	}
	
	public PanelMenuPrincipal(Component[] botones) {
		
		this (botones, 50, 50);
		
	}
	
	protected void setFormato() {
		
		// Esto prepara el frame y le da la forma que quiero.
		PanelMenuPrincipal framecito = this;
		
		BorderLayout border = new BorderLayout(200, 50);
		JPanel centro = new JPanel();
		GridLayout gridCentral = new GridLayout(this.getComponentCount() + 2, 1, this.getEspacioAlto(), this.getEspacioLados());

		// Apendeo botones.
		centro.setLayout(gridCentral);
		centro.add(new JLabel(" "));
		
		for (Component str : this.getComponentes()) {
			
			centro.add(str);
			
		}

		// Agrego el grid al frame principal.
		framecito.setLayout(border);
		framecito.add(centro, BorderLayout.CENTER);
		
		// Pongo etiquetas vacias para que el centro no ocupe toda la pantalla.
		framecito.add(new JLabel(" "), BorderLayout.EAST);
		framecito.add(new JLabel(" "), BorderLayout.WEST);
		framecito.add(new JLabel(" "), BorderLayout.SOUTH);
		framecito.add(new JLabel(" "), BorderLayout.NORTH);
		
	}

}
