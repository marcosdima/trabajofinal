package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.vistas.VistaConsola;

public class TestDeCosas {

	public static void main(String[] args) {
			
		BlackJack juego = new BlackJack();
		VistaConsola interfaz = new VistaConsola(juego);
		
		interfaz.menuPrincipal();
		
		// No esta siguiendo bien el flujo y también validar que no se pase de 21.
		// Podría replantear lo de getDatos(), que no salte de ahí al MOSTRARMANO.
		
	}

}
