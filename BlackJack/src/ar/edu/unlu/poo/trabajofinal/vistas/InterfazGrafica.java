package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.Componente;
import ar.edu.unlu.poo.gui.Etiqueta;
import ar.edu.unlu.poo.gui.Frame;
import ar.edu.unlu.poo.gui.Panel;
import ar.edu.unlu.poo.misfunciones.Escaner;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;

public class InterfazGrafica implements IVista {

	private BlackJack controlador;
	private Frame framePrincipal;
	
	private Panel menuPrincipal;
	private Panel agregarJugadores;
	private Panel panelJuego;
	
	private ArrayList<String> nombres = new ArrayList<String>();;
	private Boolean flag = false;
	
	public InterfazGrafica(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		
		this.framePrincipal = new Frame("Black Jack");		
		this.framePrincipal.visible();
		
	}
	
	@Override
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void menuPrincipal() {
		
		Boton salir = new Boton("Salir");
		Boton jugar = new Boton("Jugar");
		
		Componente[] opciones = {jugar, salir};
		
		PanelMenuPrincipal framecito = new PanelMenuPrincipal(opciones);
		
		framecito.getComponente("Jugar").evento(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				framePrincipal.clear();
				framePrincipal.addToPrincipal(new Etiqueta("s"));
			
			}

		});;
		framecito.getComponente("Salir").evento(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}});
		
		this.framePrincipal.addToPrincipal(framecito);

	}

	@Override
	public void formularioAgregarJugador() {
	
		String nombre = "";
		
		if (this.flag) {
			
			nombre = this.nombres.get(0);
			
			if (this.nombres.size() > 1) {
				
				this.nombres.remove(0);
				
			}
			
			this.controlador.addJugador(nombre);
			
		}
		
	}

	@Override
	public void menuConfiguracion() {
		
		
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj, IJugador data) {
		
		System.out.println(msj);

	}

	@Override
	public void mostrarMano(ArrayList<IJugador> datos) {
				
		Panel framecito = this.panelJuego;
		PanelJugador prueba = new PanelJugador(datos.get(0));

		framecito.add(prueba);
		
		this.framePrincipal.clear();

		this.framePrincipal.addToPrincipal(framecito);
		
		this.menuPrincipal.oculto();
		this.agregarJugadores.oculto();

		Escaner test = new Escaner();
		test.next();
		
		System.exit(0);
		
	}

	@Override
	public void formularioSetApuesta(IJugador dato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean siONo(IMensaje msj, IJugador dato) {
		// TODO Auto-generated method stub
		return false;
	}

	// Metodos de intefazgráfica.
	private void setAgregarJugadores() {
		
		int maximo = 5;
		ModuloAgregarJugador jugador;
		PanelGrilla agregar;
		Componente[] modulos = new Componente[maximo];
		
		for (int i = 0; i < maximo; i++) {
			
			jugador = new ModuloAgregarJugador(String.valueOf(i));
			modulos[i] = jugador;

		}
		
		agregar = new PanelGrilla(modulos, 2, 3);

		agregar.visible();
		
		this.framePrincipal.addToPrincipal(agregar);
		
	}
	
}

/*
 * for (ModuloAgregarJugador campo : campos) {
			
			campo.evento(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String name = campo.getString();
					
					nombres.add(name);
					
					campo.apagar();
					
				}
				
			});
			
		}
 * 
 * 
 * */
