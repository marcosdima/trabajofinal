package ar.edu.unlu.poo.trabajofinal;

public class Persona {

	private String nombre;
	private int dinero;


	public Persona(String nombre, int dinero) {
		super();
		this.setNombre(nombre);
		this.setDinero(dinero);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

}
