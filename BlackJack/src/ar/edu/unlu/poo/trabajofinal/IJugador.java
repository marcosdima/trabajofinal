package ar.edu.unlu.poo.trabajofinal;

public interface IJugador {
	
	public String getNombre();
	
	public String[] getCartas();
	
	public boolean todaviaNoJugo();
	
	public int getDinero();
	
	public int getPuntaje();

	public EstadoDeMano getEstadoDeMano();
	
	public Comparativo compararManos(IJugador player);
	
}
