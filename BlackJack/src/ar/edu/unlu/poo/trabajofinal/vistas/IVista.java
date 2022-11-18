package ar.edu.unlu.poo.trabajofinal.vistas;

import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;

public interface IVista {
		
	public void setControlador(BlackJack controlador);
	
	public void menuPrincipal();
	
	public void formularioAgregarJugador();
	
	public void menuConfiguracion();

	public void mostrarMensaje(IMensaje event, IJugador data);
	
	public void mostrarMano(ArrayList<IJugador>  datos);
	
	public void formularioSetApuesta(IJugador dato);

	public boolean siONo(IMensaje msj, IJugador dato);
}
