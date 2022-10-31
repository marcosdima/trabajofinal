package ar.edu.unlu.poo.misfunciones;

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
	
}
