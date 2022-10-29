package ar.edu.unlu.poo.trabajofinal.commons;

import java.util.Scanner;

public class Menu {
	
	private IOpciones opciones;
	private Scanner sc;
	private String titulo;
	
	public Menu(String titulo, IOpciones op) {
		
		this.setOpciones(op);
		this.setTitulo(titulo);
		sc = new Scanner(System.in);
		
	}
	
	public void setTitulo(String title) {
		this.titulo = title;
	}

	public void setOpciones(IOpciones opciones) {
		this.opciones = opciones;
	}
	
	public int print() {
		
		System.out.println(this.titulo);
		System.out.println(" ");
		
		for (String linea : this.opciones.getOpciones()) {
			
			System.out.println(linea);
			
		}
		
		System.out.println(" ");
		System.out.println("Ingrese un número del 1 al " + Integer.toString(this.opciones.size()) + ": ");
		int numero = sc.nextInt();
		
		return numero;
		
	}
	
	

}
