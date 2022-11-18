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

				this.notificar(Evento.JUGADORCARGADO, player);
				
			}
			else {
				
				this.notificar(Error.ERRORMAXJUGADORES, player);
				
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
					
					seteado = this.notificar(Error.ERRORFALTADEDINERO, player);
					
				}
				else if (monto < apuestaMinima) {
					
					seteado = this.notificar(Error.ERRORAPUESTA);
					
				}
				else if (monto == 0) {
					
					player.aposto();
					player.noSigue();
					seteado = this.notificar(Evento.NOAPUESTA, player);
					
				}
				else {
					
					player.setApuesta(apuesta);
					player.aposto();
					seteado = this.notificar(Evento.APUESTASETEADA, player);
				
				}		
				
			}	
			
		}
		
	}
	
	// HAY QUE MODIFICARLO!!!!
	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	public ArrayList<IJugador> getDatosJugadores() {
		
		boolean sigue;
		ArrayList<IJugador> datosDeJugadores = new ArrayList<IJugador>(this.jugadores.size() + 1);
		
		for (JugadorBlackJack player : this.jugadores) {
			
			sigue = player.sigueJugando();
			
			if (sigue) {
				
				datosDeJugadores.add(player);
				
			}

		}
		
		// Por último, agrego al crupier para que quede en la última posición.
		datosDeJugadores.add(this);
		
		return datosDeJugadores;
		
	}
		
	public Carta darCarta() {
		
		return this.getMazo().agarrarCarta();
		
	}
	
	// Revisa el estado del jugador y devuelve un enumerado con el resultado.
	private EstadoManoBlackJack checkEstadoJugador(Jugador player) {
		
		Mano mano;
		ArrayList<Carta> cartas;
		int tam;
		int puntaje;
		
		EstadoManoBlackJack res = null;

		if (player != null) {
			
			mano = player.getManoActual();
			cartas = mano.getCartas();
			tam = cartas.size();
			puntaje = mano.getPuntaje();
			
			if ((tam == 2) && (puntaje == 21)) {
				
				res = EstadoManoBlackJack.BLACKJACK;
				
			}
			else if (puntaje < 21) {
				
				res = EstadoManoBlackJack.MENORA21;
				
			}
			else if (puntaje > 21) {
				
				res = EstadoManoBlackJack.MAYORA21;
				
			}
			else {
				
				res = EstadoManoBlackJack.IGUALA21;
				
			}
			
			res.setPuntos(puntaje);
			mano.setEstado(res);
			
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
	public IJugador getApostador() {
		
		boolean seteado = false;
		IJugador contenedor = null;
		
		for (JugadorBlackJack player : this.jugadores) {
			
			if (player.todaviaNoAposto() && (!seteado)) {
				
				contenedor = player;
				seteado = true;
				
			}
			
		}
		
		return contenedor;
		
	}
	
	// Este metodo se encarga de repartir la carta a los JugadoresBlackJack.
	public void repartir(JugadorBlackJack player) {
		
		// Repensar el sistema para repartir, corta.
		EstadoManoBlackJack estatus;
		boolean primeraMano = false;
		boolean terminar = false;
		Carta cartita;
		
		// Si es la primera mano, muestra sus cartas y pone en true la variable 'primeraMano'.
		if (player.primeraMano()) {

			primeraMano = true;
			player.mostrarCartas();
			
		}
		
		estatus = this.checkEstadoJugador(player);
		
		if ((primeraMano) && (estatus == EstadoManoBlackJack.BLACKJACK)) {

			this.notificar(Evento.BLACKJACK, player);
			terminar = true;
			
		}
		else if (primeraMano) {
			
			this.notificar(Evento.PREGUNTAROTRA, player);
			
		}
		else {
			
			cartita = this.darCarta();
			player.addCarta(cartita);
			player.mostrarCartas();
			
			estatus = this.checkEstadoJugador(player);
			
			if (estatus == EstadoManoBlackJack.MENORA21) {

					this.notificar(Evento.PREGUNTAROTRA, player);
			
			}
			else {
				
				terminar = true;
				
			}
			
		}
		
		if (terminar) {
			
			this.terminarTurnoJugador(player);
			
		}
			
	}
	
	// Rutina para que se de cartas el crupier.
	public void repartirCrupier() {

		EstadoManoBlackJack estado = this.checkEstadoJugador(this);
		boolean jugadorLeGana = false;
		int contadorDeGanadas = 0;
		int mitad = 0;
		boolean terminar = false;

		// COREGIR ACA!!!!!!
		
		if (this.primeraMano()) {
			
			this.mostrarCartas();
		
		}
		
		// Si su mano no supera ni iguala los 21, hay que decidir si toma otra carta o termina la mano.
		if (estado == EstadoManoBlackJack.MENORA21) {
		
			for (JugadorBlackJack player : this.getJugadores()) {
				
				jugadorLeGana = player.getManoActual().getEstado().esMejorQue(estado);
				System.out.println(jugadorLeGana);;
				
				if (!jugadorLeGana) {
					
					contadorDeGanadas++;
					
				}
				
			}
			
			mitad = Integer.divideUnsigned(this.getJugadores().size() + 1, 2); // Le sumo uno, contando al crupier. Esto porque cuando es un solo jugador la div = 0;
			
			if (contadorDeGanadas > mitad) {
				
				this.addCarta(this.darCarta());			
				
			}
			else {
				
				terminar = true;
				
			}
			
		}
		else {
			
			terminar = true;
			
		}
		
		if (terminar) {
			
			this.definirGanadores();
			this.reiniciarMano();	
			this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		
	}
	
	private void reiniciarMano() {
		// TODO Auto-generated method stub
		
	}

	private void definirGanadores() {
		
		// Falta caso Black Jack y empate.
		
		boolean ganaJugador = false;
		
		for (JugadorBlackJack player : this.jugadores) {
			
			// Esto se supone que no hace falta, lo pongo por las dudas.
			if (player.sigueJugando()) {
			
				ganaJugador = player.getManoActual().getEstado().esMejorQue(this.getManoActual().getEstado());
				
				if (ganaJugador) {
					
					player.giveDinero(player.getApuesta().getGanancia());
					
				}
			
			}
		
		}
		
	}

	public boolean primeraMano() {
		
		boolean res = false;
		
		for (Carta cartita : this.getManoActual().getCartas()) {
			
			if (!cartita.esVisible()) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}
	
	public void terminarTurnoJugador(JugadorBlackJack player) {

		player.yaJugo();
		this.notificar(Evento.TERMINOTURNO, player);

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
	public boolean notificar(IMensaje mensaje, IJugador data) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje, data);
			
		}
		
		return true;
		
	}
	
	@Override
	public boolean notificar(IMensaje mensaje, ArrayList<IJugador> actuDatos) {
		
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
