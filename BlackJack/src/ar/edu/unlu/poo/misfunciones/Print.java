package ar.edu.unlu.poo.misfunciones;

import java.util.ArrayList;

public class Print {
	
	private char separador;
	private static final int MAXIMO = 2;
	private static final int MAXIMOLINEA = 50;
	
	public Print(char caracter) {
		
		this.setSeparador(caracter);
		
	}
	
	// Constructor que no recibe nada de parametro, setea por defecto como separador '-'.
	public Print() {
		
		this ('-');
		
	}
	
	// Setea el separador.
	public void setSeparador(char caracter) {
		
		this.separador = caracter;
		
	}
	
	// Muestra en pantalla lo que se le pase por parametro.
	public void print(String algo) {
		
		System.out.println(algo);
		
	}

	// Hace un print vacio.
	public void print() {
		
		System.out.println("");
		
	}
	
	// Printea sin salto de linea.
	public void justPrint(char c) {
		
		System.out.print(c);
		
	}
	
	// Printea sin salto de linea n veces.
	public void justPrint(char c, int n) {
		
		int i;
		
		for (i = 0; i < n; i++) {
			
			this.justPrint(c);
			
		}
		
	}
	
	// Hace prints vacios tantas veces como se le indique por parametro.
	public void printsEnBlanco(int espacios) {
		
		int i;
		
		for (i = 0; i < Print.MAXIMO; i++) {
			
			this.print();
			
		}
		
	}
	
	// Hace un print de algo y luego hace un print vacio.
	public void printConEspacio(String algo) {
		
		this.print(algo);
		this.print();
		
	}
	
	// Hace un print vacio y luego printea algo.
	public void printConEspacioAlto(String algo) {
	
		this.print();
		this.print(algo);
		
	}
	
	// Hace MAXIMO veces prints vacios, para luego agregar una linea divisoria y volver a hacer prints vacios.
	public void espacio() {
		
		this.printsEnBlanco(Print.MAXIMO);
		
		this.justPrint(this.separador, Print.MAXIMOLINEA);
		
		this.printsEnBlanco(Print.MAXIMO);
		
	}

	public void printSeguido(String[] printeable, int size, String espacio) {
		
		String linea = "";
		int agregado;
		int i;
		
		for (String str : printeable) {
			
			linea += str;
			agregado = size;
			
			if (str != null) {
				
				agregado = size - str.length();
				
			}
			
			
			for (i = 0; i < agregado; i++) {
				
				linea += espacio;
				
			}
			
		}
		
		this.printConEspacio(linea);
		
	}
	
	public void printSeguido(String[] printeable, int size) {
		
		this.printSeguido(printeable, size, " ");
		
	}

	public void printSeguido(ArrayList<String[]> printeable, int size) {
		
		String espacio = "";
		String[] arregloAuxiliar;
	
		int i;
		int o;
		int a;
		int mayor = 0;
		int largo = printeable.size();
		
		for (String[] arreglo : printeable) {
			
			if (arreglo.length > mayor) {
				
				mayor = arreglo.length;
				
			}
			
		}
		
		for (a = 0; a < size; a++) {
			
			espacio += " ";
			
		}
		
		for (i = 0; i < printeable.size(); i++) {
			
			arregloAuxiliar = new String[largo];
			
			for (o = 0; o < printeable.size(); o++) {
				
				try {
					
					arregloAuxiliar[o] = printeable.get(o)[i];
					
				}
				catch(Exception e) {
					
					arregloAuxiliar[o] = " ";

				}
			
			}
			
			this.printSeguido(arregloAuxiliar, size);
			
		}
		
	}
}
