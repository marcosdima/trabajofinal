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
	private int apuestaMinima;
	
	public BlackJack() {
		
		crupier = new CrupierBlackJack(MAXIMODEJUGADORES);
		this.crupier.agregarObservador(this);
		interfaces = new ArrayList<IVista>(2);
		this.setApuestaMinima(100);
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

	public void terminarMano() {
		
		this.crupier.terminarMano();
		
	}
	
	public void setApuestaMinima(int montoMinimo) {
		
		this.apuestaMinima = montoMinimo;
		
	}

	public int getApuestaMinima() {
		
		return this.apuestaMinima;
		
	}
	
	public void apostar(int monto) {
		
		this.crupier.setApuestas(this.apuestaMinima, monto);
		
	}
	
	public void getDatosJugadores() {

		this.crupier.getDatosJugadores();
		
	}
	
	//////////////////////////////////
	// Implementación de Observador //
	//////////////////////////////////
	
	@Override
	public void actualizar(IMensaje event, Object objeto) {
		
		for (IVista vista: this.interfaces) {
			
			
			switch ((Evento) event) {
					
				case PRIMERAREPARTIDA:
					
					this.setInicio();
					this.getDatosJugadores();
					vista.formularioSetApuesta(this.crupier.getApostador());
					
				case JUGAR:
					
					this.crupier.repartir(true);
					
				case PREGUNTAROTRA:
					
					vista.mostrarMensaje(event);
					this.crupier.repartir(vista.siONo(event));
					
				default:;
				
			}
		
		}
		
	}
	
	@Override
	public void actualizar(IMensaje event, ArrayList<DatosDeJugador> objeto) {
		
		for (IVista vista: this.interfaces) {
			
			switch ((Evento) event) {

			case MOSTRARMANO:
				
				vista.mostrarMano(objeto);
				
			default:
				
					break;
		
			}
				
		}
		
	}

	@Override
	public void actualizar(IMensaje event, JugadorBlackJack objeto) {
			
		for (IVista vista: this.interfaces) {
			
			vista.mostrarMensaje(event);
			
			switch ((Evento) event) {
			
			case JUGADORCARGADO:
				
				vista.formularioAgregarJugador();
			
			case APUESTASETEADA:
				
				DatosDeJugador datazo = new DatosDeJugador(objeto);
				vista.formularioSetApuesta(datazo);
				
			default:;
		
				}
			
			}
			
	}

	// No sé si debería hacerlo así.
	@Override
	public void actualizar(IMensaje event) {
		
		this.actualizar(event, new Object());
		
	}

	
}
