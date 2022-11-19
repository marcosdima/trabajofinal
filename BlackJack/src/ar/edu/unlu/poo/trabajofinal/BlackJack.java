package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Observador;
import ar.edu.unlu.poo.trabajofinal.vistas.IVista;

public class BlackJack implements Observador {
	
	private CrupierBlackJack crupier;
	private ArrayList<IVista> interfaces;
	public static final int MAXIMODEJUGADORES = 4;
	private static int DINEROBASE = 1000;
	private static int JUGADORES;

	public BlackJack() {
		
		crupier = new CrupierBlackJack(MAXIMODEJUGADORES);
		this.crupier.agregarObservador(this);
		interfaces = new ArrayList<IVista>(2);
		BlackJack.JUGADORES = 0;
		
	}
	
	public void addIntefaz(IVista intefaz) {
		
		this.interfaces.add(intefaz);
		
	}
	
	public void addJugador(String nombre) {
		
		if (nombre == "") {
			
			nombre = "Player " + BlackJack.JUGADORES;
			
		}

		this.crupier.addJugador(nombre, BlackJack.DINEROBASE);
		
	}

	public void setInicio() {
		
		this.crupier.repartirPrimeraTanda();
		
	}
	
	public void apostar(int monto) {
		
		this.crupier.setApuestas(monto);
		
	}
	
	public void getDatosJugadores() {

		this.crupier.getDatosJugadores();
		
	}
	
	public void reiniciarMano() {
		
		this.crupier.definirGanadores();
		this.crupier.reiniciarMano();
		this.setInicio();
		
	}
	
	public int getApuestaMinima() {
		
		return this.crupier.getApuestaMinima();
		
	}
	
	private void terminarPartida() {
		// TODO Auto-generated method stub
		
	}
	
	//////////////////////////////////
	// Implementación de Observador //
	//////////////////////////////////
	
	@Override
	public void actualizar(IMensaje event, ArrayList<IJugador> objeto) {
		
		IVista vista = this.interfaces.get(0);
			
		switch ((Evento) event) {

		case MOSTRARMANO:
			
			vista.mostrarMano(objeto);
			
		default:;
	
		}
				
	}

	public void actualizar(IMensaje event, IJugador data) {
		
		IVista vista = this.interfaces.get(0);
		IJugador playerContenedor = null;
		JugadorBlackJack jugadorBJ = null;
		
		switch ((Evento) event) {
		
			case JUGADORCARGADO:

				vista.mostrarMensaje(event, data);
				vista.formularioAgregarJugador();		
				
			case PREGUNTAROTRA:
				
				this.actualizar(Evento.MOSTRARMANO, this.crupier.getDatosJugadores());
				jugadorBJ = this.crupier.seleccionarJugador();
				
				if (vista.siONo(event, data)) {
					
					this.crupier.repartir(jugadorBJ);
					
				}
				else {
					
					this.crupier.terminarTurnoJugador(jugadorBJ);
					
				}	
				
			case BLACKJACK:
				
				vista.mostrarMensaje(event, data);
				break;
				
			case APUESTASETEADA:
			
				playerContenedor = this.crupier.getApostador();
				
				if (playerContenedor != null) {
					
					vista.mostrarMensaje(event, data);
					vista.formularioSetApuesta(playerContenedor);
					
				}
				else {
					
					this.crupier.repartir(this.crupier.seleccionarJugador());
					
				}
				
			case TERMINOTURNO:
				
				vista.mostrarMensaje(event, data);

		default:;
	
			}
		
	};

	@Override
	public void actualizar(IMensaje event) {
		
		IVista vista = this.interfaces.get(0);
		
		switch ((Evento) event) {
								
			case PRIMERAPUESTA:

				vista.formularioSetApuesta(this.crupier.getApostador());
				
			case TERMINOLAMANO:
				
				this.reiniciarMano();
				
				if (this.crupier.seguimosJugando()) {
					
					vista.mostrarMensaje(Evento.FINDEMANO, this.crupier);;
					this.actualizar(Evento.MOSTRARMANO, this.crupier.getDatosJugadores());
					vista.formularioSetApuesta(this.crupier.getApostador());
					
				}
				else {
					
					this.terminarPartida();
					
				}				
				
			default:;
			
		}
		
	}


	
}
