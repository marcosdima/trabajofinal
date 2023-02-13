package ar.edu.unlu.poo.trabajofinal;

import java.io.Serializable;

public interface IJugador extends Serializable {
	
	public String getNombre();
	
	public String[] getCartas();
	
	public String[] getIdCartas();
	
	public boolean todaviaNoJugo();
	
	public int getDinero();
	
	public int getPuntaje();

	public Mano getManoActual();
	
	// Dudoso...
	public EstadoDeMano getEstadoDeMano();
	
}
