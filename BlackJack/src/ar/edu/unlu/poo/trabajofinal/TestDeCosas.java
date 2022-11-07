package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;

public class TestDeCosas {

	public static void main(String[] args) {
			
		BlackJack juego = new BlackJack();
		VistaConsola interfaz = new VistaConsola(juego);
		
		interfaz.menuPrincipal();
		
		// Cuando apuesta le da otra carta y no completa el ciclo de apuestas. Error con el puntaje.
		
	}

}
