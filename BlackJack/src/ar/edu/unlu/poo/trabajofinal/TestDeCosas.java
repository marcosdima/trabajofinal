package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;

public class TestDeCosas {

	public static void main(String[] args) {
			
		BlackJack juego = new BlackJack();
		VistaConsola interfaz = new VistaConsola(juego);
		
		interfaz.menuPrincipal();
		
		// Seguir turno del crupier.
		
	}

}
