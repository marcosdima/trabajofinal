package ar.edu.unlu.poo.mistads;

public class Pila {

	private int tam;
	private int nroNodos;
	private NodoPila tope;
	
	private final int MAX = 1000;
	
	public Pila(int tam) {
		
		this.setTam(tam);
		this.tope = new NodoPila();
		this.nroNodos = 0;
		
	}
	
	public Pila() {
		this (10);
	}
	
	// Setea el tamaño.
	private void setTam(int tam) {
		
		if ((tam <= this.MAX) && (tam > 0)){
			
			this.tam = tam;
			
		}
		
	}
	
	// Retorna el tamaño.
	public int getTam() {
		return this.tam;
	}
	
	// Retorna el nodo tope.
 	public NodoPila getTope() {
		return this.tope;
	}
	
	// Retorna true si el tope es nulo.
	public boolean estaVacia() {
		
		boolean res = false;
		
		if (this.tope == null) {
			
			res = true;
			
		}
		
		return res;
		
	}
	
	// Retorna true si esta llena.
	public boolean estaLlena() {
		
		boolean res = false;
		
		if (this.nroNodos == this.tam) {
			
			res = true;
		
		}
		
		return res;
		
	}
	
	// Retorna la cantidad de nodos.
	public int getCant() {
		return this.nroNodos;
	}
	
	// Define a tope como tope.anterior (Puede ser nulo, si se desapila el único elemento de la pila).
	public void desapilar() {
		
		boolean vacia = this.estaVacia();
				
		if (!vacia) {
			this.tope = this.tope.getAnterior();
			this.nroNodos--;	
		}
		
	}
	
	// Apila el elemento (Si no esta llena ni el elemento es null).
	public void apilar(Dato elemento) {
		
		boolean llena = this.estaLlena();
		
		NodoPila elementoAux = new NodoPila(elemento);
		
		if ((!llena)){
			
			elementoAux.setAnterior(this.tope);
			this.tope = elementoAux;
			this.nroNodos++;
			
		}
		
	}
	
	// Retorna un array con todas las claves.
	public String[] getDatos() {
		
		int iteraciones;
		boolean vacia = this.estaVacia();
		String[] claves;
		Pila pilaAux = new Pila(this.tam);
		
		if (!vacia) {
			
			claves = new String[this.nroNodos];
			iteraciones = this.nroNodos;
			
			for (int i = 0; i < iteraciones; i++) {
				
				claves[i] = this.getTope().getDato().getClave();
				
				pilaAux.apilar(this.tope.getDato());
				this.desapilar();
				
			}		
			
			pilaAux.pasarContenido(this);
			
		}
		else {
			
			claves = null;
			
		}
		
		
		return claves;
		
	}

	// Pasa el contenido de la pila, a la otra (invirtiendolo).
	public void pasarContenido(Pila pila) {
		
		boolean vacia = this.estaVacia();
		pila = new Pila(this.tam);
		
		if ((!vacia)) {
			
			for (int i = 0; i < this.nroNodos; i++) {
				
				pila.apilar(this.tope.getDato());
				this.desapilar();
				
			}
			
		}
		
	}
	
	// Retorna true si esta pila y la que pasan por parámetro tienen el mismo tamaño.
	public boolean sameTam(Pila pila) {
		
		boolean res = (this.tam == pila.getTam());
		return res;
		
	}
	
	// Retorna un string con todos los datos separados con coma. (No programado)
	public String returnCSV() {
		
		return "";
		
	}
	
}
