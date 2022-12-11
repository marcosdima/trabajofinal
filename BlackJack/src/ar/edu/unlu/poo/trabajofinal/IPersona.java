package ar.edu.unlu.poo.trabajofinal;

import java.rmi.RemoteException;

public interface IPersona {
	
	public int getDinero() throws RemoteException;

	public String getNombre() throws RemoteException;
	
}