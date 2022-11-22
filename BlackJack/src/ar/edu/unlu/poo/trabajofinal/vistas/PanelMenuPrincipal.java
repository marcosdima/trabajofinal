package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.Componente;
import ar.edu.unlu.poo.gui.Etiqueta;
import ar.edu.unlu.poo.gui.Panel;

public class PanelMenuPrincipal extends PanelMenu {
	
	public PanelMenuPrincipal(Componente[] botones, int alto, int lados) {
		
		super(botones, alto, lados);
		this.setFormato();
		
	}
	
	public PanelMenuPrincipal(Componente[] botones) {
		
		this (botones, 50, 50);
		
	}
	
	protected void setFormato() {
		
		// Esto prepara el frame y le da la forma que quiero.
		Panel framecito = this;
		
		BorderLayout border = new BorderLayout(200, 50);
		Panel centro = new Panel();
		GridLayout gridCentral = new GridLayout(this.getBotonTags().length + 2, 1, this.getEspacioAlto(), this.getEspacioLados());
		
		Boton botonAux;

		// Apendeo botones.
		centro.setLayout(gridCentral);
		centro.add(Etiqueta.vacio());
		
		for (String str : this.getBotonTags()) {
			
			botonAux = new Boton(str);
			this.getComponentes().add(botonAux);
			centro.add(botonAux);
			
		}

		// Agrego el grid al frame principal.
		framecito.setLayout(border);
		framecito.add(centro, BorderLayout.CENTER);
		
		// Pongo etiquetas vacias para que el centro no ocupe toda la pantalla.
		framecito.add(Etiqueta.vacio(), BorderLayout.EAST);
		framecito.add(Etiqueta.vacio(), BorderLayout.WEST);
		framecito.add(Etiqueta.vacio(), BorderLayout.SOUTH);
		framecito.add(Etiqueta.vacio(), BorderLayout.NORTH);
		
	}

}
