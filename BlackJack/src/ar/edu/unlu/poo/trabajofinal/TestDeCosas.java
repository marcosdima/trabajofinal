package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;

public class TestDeCosas {

	public static void main(String[] args) {
					
		BlackJack juego = new BlackJack();
		VistaConsola interfaz = new VistaConsola(juego);
		
		interfaz.menuPrincipal();
		
		// Errores al empezar el turno.
		// Cuando apueste monto 0 no apuesta.
		// Revisar el getPuntaje() con los as...
		
	}

}
