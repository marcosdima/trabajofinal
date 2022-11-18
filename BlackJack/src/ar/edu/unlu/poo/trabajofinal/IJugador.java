package ar.edu.unlu.poo.trabajofinal;

public interface IJugador {
	
	public String getNombre();
	
	public String[] getCartas();
	
	public boolean todaviaNoJugo();
	
	public boolean sigueJugando();
	
	public int getDinero();
	
	public int getPuntaje();

}
