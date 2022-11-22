package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import ar.edu.unlu.poo.gui.Etiqueta;
import ar.edu.unlu.poo.gui.Panel;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public class PanelJugador extends Panel {

	IJugador jugador;
	
	public PanelJugador(IJugador player) {
		
		super();
		this.jugador = player;
		this.setFormato();
		
		
	}
	
	private void setFormato() {
		
		int espacio = 50;
		int espacioCartas = 10;
		
		Panel panelNorte = new Panel();
		Panel panelCentro = new Panel();
		
		BorderLayout mayor = new BorderLayout();
		BorderLayout norte = new BorderLayout();
		//GridLayout cartas = new GridLayout(3, 3, espacioCartas, espacioCartas);
		GridLayout cartas = new GridLayout(1, this.jugador.getCartas().length, espacioCartas, espacioCartas);
		
		Etiqueta nombre = new Etiqueta(this.jugador.getNombre());
		Etiqueta dinero = new Etiqueta("Dinero: " + this.jugador.getDinero());
		Etiqueta puntos = new Etiqueta("Puntaje: " + this.jugador.getPuntaje());
		
		this.setLayout(mayor);
		panelNorte.setLayout(norte);
		panelCentro.setLayout(cartas);
		
		// Seteo panel norte
		panelNorte.add(nombre, BorderLayout.NORTH);
		panelNorte.add(dinero, BorderLayout.SOUTH);
		
		// Seteo panel central
		for (String cartita : this.jugador.getCartas()) {
			
			panelCentro.add(new Etiqueta(cartita));
			
		}
		
		// Appendeo
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(puntos, BorderLayout.SOUTH);
		
	};
	
	
}
