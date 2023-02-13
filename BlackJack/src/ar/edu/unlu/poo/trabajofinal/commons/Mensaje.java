package ar.edu.unlu.poo.trabajofinal.commons;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = -6072826364727256218L;
	private IMensaje tag;
	private Object remitente;
	
	public Mensaje(IMensaje tag, Object remitente) throws RemoteException {
		
		this.tag = tag;
		this.remitente = remitente;
		
	}

	public IMensaje getTag() throws RemoteException {
		return tag;
	}

	public Object getRemitente() throws RemoteException {
		return remitente;
	}
	
	
	
}
