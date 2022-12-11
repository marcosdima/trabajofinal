package ar.edu.unlu.poo.trabajofinal;

import java.rmi.RemoteException;

public interface IJugador {
	
	public String getNombre() throws RemoteException;
	
	public String[] getCartas() throws RemoteException;
	
	public String[] getIdCartas() throws RemoteException;
	
	public boolean todaviaNoJugo() throws RemoteException;
	
	public int getDinero() throws RemoteException;
	
	public int getPuntaje() throws RemoteException;

	public EstadoDeMano getEstadoDeMano() throws RemoteException;
	
	public Mano getManoActual() throws RemoteException;
	
}
