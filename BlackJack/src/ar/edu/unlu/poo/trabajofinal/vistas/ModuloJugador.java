package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.edu.unlu.poo.trabajofinal.IJugador;

public class ModuloJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	IJugador jugador;
	
	public ModuloJugador(IJugador player) {
		
		super();
		this.jugador = player;
		this.setFormato();
		
		
	}
	
	private void setFormato() {
		
		int espacio = 50;
		int espacioCartas = 20;

		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		
		BorderLayout mayor = new BorderLayout();
		BorderLayout norte = new BorderLayout();
		//GridLayout cartas = new GridLayout(3, 3, espacioCartas, espacioCartas);
		GridLayout cartas = new GridLayout(1, this.jugador.getCartas().length, espacioCartas, espacioCartas);
		
		JButton nombre = new JButton(this.jugador.getNombre());
		JLabel dinero = new JLabel("Dinero: " + this.jugador.getDinero());
		JLabel puntos = new JLabel("Puntaje: " + this.jugador.getPuntaje());
		
		this.setLayout(mayor);
		panelNorte.setLayout(norte);
		panelCentro.setLayout(cartas);
		
		// Seteo panel norte
		panelNorte.add(nombre, BorderLayout.NORTH);
		panelNorte.add(dinero, BorderLayout.SOUTH);
		
		// Seteo panel central
		for (String cartita : this.jugador.getCartas()) {
			
			panelCentro.add(new JLabel(cartita));
			
		}
		
		// Appendeo
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(puntos, BorderLayout.SOUTH);
		
	};
	
	
}
