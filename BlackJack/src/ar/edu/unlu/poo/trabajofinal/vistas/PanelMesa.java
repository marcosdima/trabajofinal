package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class PanelMesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelGrilla principal;
	private Component[] componentes;
	private JPanel[] zonas;
	private JPanel jugadores;
	private JPanel crupier;

	public PanelMesa(Component[] botones) {
		
		this.zonas = new JPanel[2];
		this.componentes = botones;
		this.setJugadores();
		this.setCrupier();
		this.setPrincipal();

		
	}

	private void setPrincipal() {
		
		this.principal = new PanelGrilla(this.zonas, 2, 1);
		this.add(principal);
		
	}

	private void setJugadores() {
		
		int i;
		int largo = this.componentes.length - 1;
		
		JPanel aux = new JPanel();
		FlowLayout lay = new FlowLayout();
				
		aux.setLayout(lay);

		for (i = 0; i < largo; i++) {
			
			aux.add(this.componentes[i]);
			
		}
		
		this.jugadores = aux;
		this.zonas[0] = this.jugadores;
		
	}
	
	private void setCrupier() {
		
		JPanel aux = new JPanel();
		FlowLayout lay = new FlowLayout();
				
		aux.setLayout(lay);
		aux.add(this.componentes[this.componentes.length - 1]);
		
		this.crupier = aux;
		
		this.zonas[1] = this.crupier;
		
	}

}
