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
		
		IVista vista = this.interfaces.get(0);
		
		switch ((Evento) event) {
				
			case PRIMERAREPARTIDA:
				
				this.setInicio();
				this.getDatosJugadores();
				
			case PREGUNTAROTRA:
				
				vista.mostrarMensaje(event);
				this.crupier.repartir(vista.siONo(event));
				
			case PRIMERAPUESTA:
				
				DatosDeJugador datazoUno = this.crupier.getApostador();
				vista.formularioSetApuesta(datazoUno);
				
			case APUESTASETEADA:
				
				DatosDeJugador datazoDos = this.crupier.getApostador();
				
				if (datazoDos != null) {
					
					vista.mostrarMensaje(event);
					vista.formularioSetApuesta(datazoDos);
					
				}
				else {
					
					this.crupier.repartir(true);
					
				}
				
			default:;
			
		}
		
	}
	
	@Override
	public void actualizar(IMensaje event, ArrayList<DatosDeJugador> objeto) {
		
		IVista vista = this.interfaces.get(0);
			
		switch ((Evento) event) {

		case MOSTRARMANO:
			
			vista.mostrarMano(objeto);
			
		default:;
	
		}
				
	}

	@Override
	public void actualizar(IMensaje event, JugadorBlackJack objeto) {
			
		IVista vista = this.interfaces.get(0);
			
		vista.mostrarMensaje(event);
		
		switch ((Evento) event) {
		
		case JUGADORCARGADO:
			
			vista.formularioAgregarJugador();

		default:;
	
			}
			
	}

	// No sé si debería hacerlo así.
	@Override
	public void actualizar(IMensaje event) {
		
		this.actualizar(event, new Object());
		
	}

	
}
