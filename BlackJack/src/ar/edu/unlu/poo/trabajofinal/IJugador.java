package ar.edu.unlu.poo.trabajofinal;

public interface IJugador {
	
	public String getNombre();
	
	public String[] getCartas();
	
	public String[] getIdCartas();
	
	public boolean todaviaNoJugo();
	
	public int getDinero();
	
	public int getPuntaje();

	// Dudoso...
	public EstadoDeMano getEstadoDeMano();
	
}
