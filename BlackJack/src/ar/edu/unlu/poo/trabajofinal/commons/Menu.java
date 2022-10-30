package ar.edu.unlu.poo.trabajofinal.commons;

import ar.edu.unlu.poo.misfunciones.Print;

public class Menu {
	
	private IOpciones opciones;
	private Print p;
	
	public Menu(String titulo, IOpciones op) {
		
		this.setOpciones(op);
		p = new Print();
		
	}
	
	public Menu(OpcionesMenuPrincipal op) {
		
		this ("Menu Principal", op);
		
	}

	public void setOpciones(IOpciones opciones) {
		this.opciones = opciones;
	}
	
	public void print() {
		
		p.printConEspacio(this.opciones.getTitulo());
		
		for (String linea : this.opciones.getOpciones()) {
			
			p.print(linea);
			
		}
		
		p.printConEspacioAlto("Ingrese un número del 1 al " + Integer.toString(this.opciones.size()) + ": ");
		
	}

}
