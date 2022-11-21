package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.Etiqueta;
import ar.edu.unlu.poo.gui.Frame;
import ar.edu.unlu.poo.gui.Panel;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;

public class InterfazGrafica implements IVista {

	private BlackJack controlador;
	private Frame framePrincipal;
	private Frame frameAgregarJugadores;
	private Frame frameJuego;
	
	public InterfazGrafica(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		
		this.frameAgregarJugadores = new Frame("Black Jack");
		this.frameJuego = new Frame("Black Jack");
		this.framePrincipal = new Frame("Black Jack");
		
		this.setFramePrincipal();
		this.setFrameAgregarJugadores();
	}
	
	@Override
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void menuPrincipal() {
		
		this.frameAgregarJugadores.oculto();
		this.frameJuego.oculto();
		this.framePrincipal.visible();
		
	}

	@Override
	public void formularioAgregarJugador() {
		
		this.frameAgregarJugadores.visible();
		this.frameJuego.oculto();
		this.framePrincipal.oculto();
		
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
		
		this.frameAgregarJugadores.oculto();
		this.frameJuego.visible();
		this.framePrincipal.oculto();
		
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

	
	// Interfaz gráfica.
	
	private void setFramePrincipal() {
		
		// Esto prepara el frame y le da la forma que quiero.
		Frame framecito = this.framePrincipal;
		BorderLayout border = new BorderLayout(200, 50);
		Panel centro = new Panel();
		GridLayout gridCentral = new GridLayout(5, 1, 50, 50);

		// Preparación de los botones.
		Boton botonJugar = new Boton("Jugar");
		botonJugar.evento(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				formularioAgregarJugador();
				
			}
			
		});
		
		// NO ESTA IMPLEMENTADO.
		Boton botonConfiguracion = new Boton("Configuración");
		botonConfiguracion.evento(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				formularioAgregarJugador();
				
			}
			
		});
		
		Boton botonSalir = new Boton("Salir");
		botonSalir.evento(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
			
		});
		
		// Apendeo botones.
		centro.setLayout(gridCentral);
		centro.add(Etiqueta.vacio());
		centro.add(botonJugar);
		centro.add(botonConfiguracion);
		centro.add(botonSalir);
		
		// Agrego el grid al frame principal.
		framecito.setLayoutPrincipal(border);
		framecito.addToPrincipal(centro, BorderLayout.CENTER);
		
		// Pongo etiquetas vacias para que el centro no ocupe toda la pantalla.
		framecito.addToPrincipal(Etiqueta.vacio(), BorderLayout.EAST);
		framecito.addToPrincipal(Etiqueta.vacio(), BorderLayout.WEST);
		framecito.addToPrincipal(Etiqueta.vacio(), BorderLayout.SOUTH);
		framecito.addToPrincipal(Etiqueta.vacio(), BorderLayout.NORTH);
		
	}
	
	private void setFrameAgregarJugadores() {
		
		Frame framecito = this.frameAgregarJugadores;
		Boton seguir = new Boton("Seguir");
		ArrayList<PanelAgregarJugador> campos = new ArrayList<PanelAgregarJugador>();
		PanelAgregarJugador jugador;

		framecito.setLayoutPrincipal(new GridLayout(2,3,20,40));
		
		for (int i = 0; i < 5; i++) {
			
			jugador = new PanelAgregarJugador();
			campos.add(jugador);
			framecito.addToPrincipal(jugador);
			
		}
		
		framecito.addToPrincipal(seguir);
		seguir.evento(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(PanelAgregarJugador campo : campos) {
					
					String nombre = campo.getNombre();
					boolean seteado = false;
					
					if (nombre != null) {
						
						System.out.println(campo.getNombre());
						controlador.addJugador(campo.getNombre());
						seteado = true;
						
					}
					
					if (seteado) {
			
						controlador.addJugador(null);
			
					}
					
				}
				
			}

		});
		
	}
	
}
