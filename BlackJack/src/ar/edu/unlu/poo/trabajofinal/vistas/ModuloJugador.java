package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.ImageManager;
import ar.edu.unlu.poo.trabajofinal.IJugador;

public class ModuloJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageManager manager;
	
	public ModuloJugador(IJugador player, ImageManager manager) {
		
		super();
		this.setManager(manager);
		this.setFormato(player);
		
		
	}
	
	private void setFormato(IJugador jugador) {

		int espacioCartas = 5;

		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		
		BorderLayout mayor = new BorderLayout();
		BorderLayout norte = new BorderLayout();
		GridLayout cartas = new GridLayout(3, 3, espacioCartas, espacioCartas);
		
		JButton nombre = new JButton(jugador.getNombre());
		JLabel dinero = new JLabel("Dinero: " + jugador.getDinero());
		JLabel puntos = new JLabel("Puntaje: " + jugador.getPuntaje());
		
		this.setLayout(mayor);
		panelNorte.setLayout(norte);
		panelCentro.setLayout(cartas);
		
		// Apago el botón por motivos estéticos.
		nombre.setEnabled(false);
		
		// Seteo panel norte
		panelNorte.add(nombre, BorderLayout.NORTH);
		panelNorte.add(dinero, BorderLayout.SOUTH);
		
		// Seteo panel central
		for (String cartita : jugador.getIdCartas()) {
			
			panelCentro.add(this.manager.imagen(cartita));
			
		}
		
		// Appendeo
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(puntos, BorderLayout.SOUTH);
		
	};
	
	public void setManager(ImageManager manager) {
		
		this.manager = manager;
		
	}
	
}
