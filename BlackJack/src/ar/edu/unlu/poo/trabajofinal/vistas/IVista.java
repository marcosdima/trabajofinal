package ar.edu.unlu.poo.trabajofinal.vistas;

import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.DatosDeJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;

public interface IVista {
		
	public void setControlador(BlackJack controlador);
	
	public void menuPrincipal();
	
	public void formularioAgregarJugador();
	
	public void menuConfiguracion();

	public void mostrarMensaje(IMensaje event);
	
	public void mostrarMano(ArrayList<DatosDeJugador>  datos);
	
	public void formularioSetApuesta(DatosDeJugador dato);

	public boolean siONo(IMensaje msj);
}
