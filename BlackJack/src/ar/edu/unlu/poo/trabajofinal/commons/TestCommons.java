package ar.edu.unlu.poo.trabajofinal.commons;

import ar.edu.unlu.poo.misfunciones.Print;

public class TestCommons {

	public static void main(String[] args) {
		
		Print p = new Print();
		
		Menu menu = new Menu(OpcionesMenuPrincipal.SALIR);

		menu.print();
		
		p.espacio();
		
		menu.print();
		
	}
	
}
