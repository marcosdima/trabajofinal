package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;
import ar.edu.unlu.poo.trabajofinal.vistas.InterfazGrafica;

public class BlackJackApp {

	public static void main(String[] args) {					
		
		BlackJack juego = new BlackJack();
		VistaConsola interfaz = new VistaConsola(juego);
		InterfazGrafica interfazGrafica = new InterfazGrafica(juego);
		
		boolean consola = false;
		boolean grafico = !consola;
		
		interfaz.setActiva(consola);
		interfazGrafica.setActiva(grafico);
		
		juego.iniciar();
		
	}

}
