package ar.edu.unlu.poo.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.unlu.poo.trabajofinal.vistas.PanelAgregarJugador;

public class TestGUI {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("COso");
		
		JPanel p = (JPanel) f.getContentPane();
		
		p.add(new JTextField());
		
		f.setVisible(true);
		
		PanelAgregarJugador pan = new PanelAgregarJugador();
		
		p.add(pan.getComponent());

	}

}
