package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;
import ar.edu.unlu.poo.trabajofinal.vistas.InterfazGrafica;

public class TestDeCosas {

	public static void main(String[] args) {
							
		BlackJack juego = new BlackJack();
		//VistaConsola interfaz = new VistaConsola(juego);
		InterfazGrafica interfaz = new InterfazGrafica(juego);
		
		interfaz.menuPrincipal();
		
		// Mirar captura de errores para recrearlos.
		// Testear errores.
		// No se que chota le pasa a la interfaz, pensar como solucionar que no sale del menú principal.
		
	}

}
