package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;
import ar.edu.unlu.poo.trabajofinal.commons.Error;
import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Observado;
import ar.edu.unlu.poo.trabajofinal.commons.Observador;

public class CrupierBlackJack extends Crupier implements Observado {

	private ArrayList<JugadorBlackJack> jugadores;
	private ArrayList<Observador> observers;
	
	public CrupierBlackJack(int nroDeJugadores) {
	
		super();
		this.setJugadores(nroDeJugadores);
		this.observers = new ArrayList<Observador>();
		this.barajar();

	}

	// Rutina que reparte la primera mano.
	public void repartirPrimeraTanda() {

		for (JugadorBlackJack player : this.getJugadores()) {
			
			if (player.sigueJugando()) {
				
				player.addCarta(this.darCarta());
				player.addCarta(this.darCarta());
				player.mostrarCarta();
				
			}
		
		}
		
		this.addCarta(this.darCarta());
		this.addCarta(this.darCarta());
		this.mostrarCarta();
		
	}

	// Appendea un jugador al ArrayList de jugadores.
	public void addJugador(String nombre, int plata) {
			
		boolean nombreNulo = (nombre == null);
		boolean noHayJugadores = this.jugadores.isEmpty();
		boolean seAgrego;
		
		if (!nombreNulo) {
			
			JugadorBlackJack player = new JugadorBlackJack(nombre, plata);
			
			seAgrego = this.jugadores.add(player);
			
			if (seAgrego) {
				
				this.notificar(Evento.JUGADORCARGADO, player.getData());
				
			}
			else {
				
				this.notificar(Error.ERRORMAXJUGADORES, player.getData());
				
			}
			
		}
		else if (noHayJugadores) {
			
			this.notificar(Error.NOHAYJUGADORESCARGADOS);
			
		}
		else {
			
			this.repartirPrimeraTanda();
			this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			this.notificar(Evento.PRIMERAPUESTA);
		
		}
		
	}

	// Rutina de fin de mano (No esta terminada, debería detectar también el fin de juego)
	public void terminarMano() {
		
		for (Jugador player : this.jugadores) {
			
			player.clearMano();
			
		}
		
		this.clearMano();
		
	}

	// Seteo de apuestas.
	public void setApuestas(int apuestaMinima, int monto) {
		
		Apuesta apuesta = new Apuesta(monto);
		boolean seteado = false;

		for (JugadorBlackJack player : this.jugadores) { 

			if ((player.todaviaNoAposto()) && (!seteado)) {
				
				if (apuesta.getMonto() > player.getDinero()) {
					
					seteado = this.notificar(Error.ERRORFALTADEDINERO, player.getData());
					
				}
				else if (monto < apuestaMinima) {
					
					seteado = this.notificar(Error.ERRORAPUESTA);
					
				}
				else if (monto == 0) {
					
					player.aposto();
					player.noSigue();
					seteado = this.notificar(Evento.NOAPUESTA, player.getData());
					
				}
				else {
					
					player.setApuesta(apuesta);
					player.aposto();
					seteado = this.notificar(Evento.APUESTASETEADA, player.getData());
				
				}		
				
			}	
			
		}
		
		this.repartir(true);
				
	}
	
	// HAY QUE MODIFICARLO!!!!
	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	public ArrayList<DatosDeJugador> getDatosJugadores() {
		
		DatosDeJugador datos;
		boolean sigue;
		ArrayList<DatosDeJugador> datosDeJugadores = new ArrayList<DatosDeJugador>(this.jugadores.size() + 1);
		
		for (JugadorBlackJack player : this.jugadores) {
			
			sigue = player.sigueJugando();
			
			if (sigue) {
				
				datos = new DatosDeJugador(player);
				datosDeJugadores.add(datos);
				
			}

		}
		
		// Por último, agrego al crupier para que quede en la última posición.
		datos = new DatosDeJugador(this);
		datosDeJugadores.add(datos);
		
		return datosDeJugadores;
		
	}
	
	// Proximamente lo voy a sacar, para la interfaz jugador.
	public DatosDeJugador getDatoDeJugador() {
		
		return new DatosDeJugador(this.seleccionarJugador());
		
	}
	
	public Carta darCarta() {
		
		return this.getMazo().agarrarCarta();
		
	}
	
	// Este metodo se encarga de repartir la carta a los JugadoresBlackJack.
	public void repartir(boolean quiereMas) {
		
		JugadorBlackJack contenedorJugador = this.seleccionarJugador();
		DatosDeJugador datazo = contenedorJugador.getData();
		EstadoMano status  = this.checkEstadoJugador(contenedorJugador);
		boolean terminarTurno = false;
		boolean primeraMano = false;
		
		System.out.println(contenedorJugador.getNombre() + contenedorJugador.primeraMano());
	
		if (contenedorJugador.primeraMano()) {
			
			contenedorJugador.mostrarCartas();
			primeraMano = true;
			
		}
		
		if ((primeraMano) && (status == EstadoMano.BLACKJACK)) {
			
			this.notificar(Evento.BLACKJACK, datazo);
			terminarTurno = true;
			
		}
		else if (primeraMano) {
			
			this.notificar(Evento.PREGUNTARPRIMERAMANO, datazo);
			
		}
		else if (quiereMas) {
			
			contenedorJugador.addCarta(this.darCarta());
			contenedorJugador.mostrarCartas();
			
			//Estado post dar carta.
			status = this.checkEstadoJugador(contenedorJugador);
			
			if (!(status == EstadoMano.MENORA21)) {
				
				terminarTurno = true;
				
			}
			else {

				this.notificar(Evento.PREGUNTAROTRA, datazo);
				
			}
			
		}
		else {
			
			terminarTurno = true;
			
		}
		
		if (terminarTurno) {

			contenedorJugador.yaJugo();
			this.notificar(Evento.TERMINOTURNO, datazo);
			this.repartir(true);
			
		}
		
		
	
	}
	
	// Revisa el estado del jugador y devuelve un enumerado con el resultado.
	private EstadoMano checkEstadoJugador(Jugador player) {
		
		Mano mano = player.getManoActual();
		ArrayList<Carta> cartas = mano.getCartas();
		int tam = cartas.size();
		int puntaje = mano.getPuntaje();
		
		EstadoMano res;

		if ((tam == 2) && (puntaje == 21)) {
			
			res = EstadoMano.BLACKJACK;
			
		}
		else if (puntaje < 21) {
			
			res = EstadoMano.MENORA21;
			
		}
		else if (puntaje > 21) {
			
			res = EstadoMano.MAYORA21;
			
		}
		else {
			
			res = EstadoMano.IGUALA21;
			
		}
		
		return res;
		
	}


	// Devuelve un jugador disponible para jugar.
	public JugadorBlackJack seleccionarJugador() {
		
		boolean seteado = false;
		
		// En casi de que no haya jugadores disponibles, retorna null.
		JugadorBlackJack contenedorJugador = null;
		
		for (JugadorBlackJack player: this.jugadores) {
			
			if ((player.todaviaNoJugo()) && (!seteado)) {
				
				contenedorJugador = player;
				seteado = true;
				
			}
			
		}

		return contenedorJugador;
		
	}
	

	// Devuelve un jugador disponible para apostar.
	public DatosDeJugador getApostador() {
		
		boolean seteado = false;
		// En casi de que hayan apostado todos, retorna null.
		DatosDeJugador dato = null;
		
		for (JugadorBlackJack player : this.jugadores) {
			
			if (player.todaviaNoAposto() && (!seteado)) {
				
				dato = new DatosDeJugador(player);
				seteado = true;
				
			}
			
		}
		
		return dato;
		
	}
	
	
/////////////////////////////////
////// Getters and Setters //////
/////////////////////////////////
	
	public void setJugadores(int n) {
		this.jugadores = new ArrayList<JugadorBlackJack>(n);
	}

	public int getPuntaje() {
		
		Mano mano = this.getManoActual();
		int puntos = mano.getPuntaje();
		ContenidoDeCarta contenido;

		for (Carta cartita : mano.getCartas()) {
			
			if (cartita.esVisible()) {
		
				contenido = cartita.getContenido();
				
				if ((contenido == ContenidoDeCarta.AS) && (puntos > 21)) {
					
					puntos = puntos - 10;
					
				}
			
			}
			
		}
		
		return puntos;
		
	}
	
	
	protected void setMazo() {
		
		MazoDeNaipes m = new MazoDeNaipes();
		this.setMazo(m);
		
	}
	
	public ArrayList<JugadorBlackJack> getJugadores() {
		return jugadores;
	}

/////////////////////////////////
// Implementación de Observado //
/////////////////////////////////
	
	@Override
	public void agregarObservador(Observador observer) {
		
		this.observers.add(observer);
		
	}

	
	@Override
	public boolean notificar(IMensaje mensaje, DatosDeJugador data) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje, data);
			
		}
		
		return true;
		
	}
	
	
	@Override
	public boolean notificar(IMensaje mensaje, ArrayList<DatosDeJugador> actuDatos) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje, actuDatos);
			
		}
		
		return true;
		
	}

	
	public boolean notificar(IMensaje mensaje) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje);
			
		}
		
		return true;
		
	}


	
}
