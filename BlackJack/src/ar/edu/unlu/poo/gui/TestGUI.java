package ar.edu.unlu.poo.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.unlu.poo.trabajofinal.JugadorBlackJack;
import ar.edu.unlu.poo.trabajofinal.vistas.*;
public class TestGUI {

	public static void main(String[] args) {
		/*
		JFrame f = new JFrame("COso");
		
		JPanel p = (JPanel) f.getContentPane();
		
		p.add(new JTextField());
		
		f.setVisible(true);
		
		PanelAgregarJugador pan = new PanelAgregarJugador();
		
		p.add(pan.getComponent());
		*/
		
		Frame f = new Frame("test");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		String[] str = {"casa", "dos"};

		p1.add(new Boton("no Caca"));
		f.addToPrincipal(p1, BorderLayout.CENTER);
		
		p2.add(new Boton("no Caca1"));
		f.addToPrincipal(p2,  BorderLayout.SOUTH);
		
		p3.add(new Boton("no Cacasdsd"));
		f.addToPrincipal(p3, BorderLayout.WEST);
		
		f.clear();
		
		//PanelJugador p = new PanelJugador(new JugadorBlackJack("Carlos", 1000));

		
		f.visible();
		

	}

}
