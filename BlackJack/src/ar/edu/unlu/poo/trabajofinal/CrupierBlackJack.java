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
	private int apuestaMinima;
	
	public CrupierBlackJack(int nroDeJugadores) {
	
		super();
		this.setJugadores(nroDeJugadores);
		this.observers = new ArrayList<Observador>();
		this.setApuestaMinima(100);

	}

	// Rutina que reparte la primera mano.
	public void repartirPrimeraTanda() {

		this.barajar();
		
		for (JugadorBlackJack player : this.getJugadores()) {
				
			player.addCarta(this.darCarta());
			player.addCarta(this.darCarta());
			player.mostrarCarta();
				
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

	// Seteo de apuestas.
	public void setApuestas(int monto) {
		
		Apuesta apuesta = new Apuesta(monto);
		boolean seteado = false;

		for (JugadorBlackJack player : this.jugadores) { 

			if (player.getDinero() < this.apuestaMinima) {
				
				this.eliminar(player);
				
			}
			else if ((player.todaviaNoAposto()) && (!seteado)) {
				
				if (apuesta.getMonto() > player.getDinero()) {
					
					seteado = this.notificar(Error.ERRORFALTADEDINERO, player);
					
				}
				else if (monto < this.apuestaMinima) {
					
					seteado = this.notificar(Error.ERRORAPUESTA);
					
				}
				else if (monto == 0) {
					
					// Podría hacer que si ningún jugador apostó, saltee la mano.
					player.aposto();
					player.yaJugo();
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

	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	public ArrayList<IJugador> getDatosJugadores() {

		ArrayList<IJugador> datosDeJugadores = new ArrayList<IJugador>(this.jugadores.size() + 1);
		
		for (JugadorBlackJack player : this.jugadores) {

			datosDeJugadores.add(player);

		}
		
		// Por último, agrego al crupier para que quede en la última posición.
		datosDeJugadores.add(this);
		
		return datosDeJugadores;
		
	}
		
	public Carta darCarta() {
		
		return this.getMazo().agarrarCarta();
		
	}
	
	// Revisa el estado del jugador y devuelve un enumerado con el resultado.
	private EstadoManoBlackJack checkEstadoJugador(Jugador player, int puntaje) {
		
		Mano mano;
		ArrayList<Carta> cartas;
		int tam;
		
		EstadoManoBlackJack res = null;

		if (player != null) {
			
			mano = player.getManoActual();
			cartas = mano.getCartas();
			tam = cartas.size();

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
		
		estatus = this.checkEstadoJugador(player, this.getPuntaje());
		
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
			
			estatus = this.checkEstadoJugador(player, player.getPuntaje());
			
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

		EstadoManoBlackJack estado;
		Comparativo comparacion;
		int contadorDeGanadas = 0;
		// Este contador de empates podría implentarlo en un futuro.
		int contadorDeEmpates = 0;
		int mitad = 0;
		boolean terminar = false;
		
		if (this.primeraMano()) {
			
			this.mostrarCartas();
		
		}
		
		estado = this.checkEstadoJugador(this, this.getPuntaje());
		
		// Si su mano no supera ni iguala los 21, hay que decidir si toma otra carta o termina la mano.
		if (estado == EstadoManoBlackJack.MENORA21) {
		
			for (JugadorBlackJack player : this.getJugadores()) {
				
				comparacion = this.compararManos(player);
				
				if (comparacion.gana()) {
					
					contadorDeGanadas++;
					
				}
				else if (comparacion.empate()) {
					
					contadorDeEmpates++;
					
				}
				
			}
			
			 // Le sumo uno, contando al crupier. Esto porque cuando es un solo jugador la div = 0;
			mitad = Integer.divideUnsigned(this.nroDeJugadores() + 1, 2);
			
			if (contadorDeGanadas < mitad) {
				
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
			
			this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			this.notificar(Evento.TERMINOLAMANO);
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		
	}

	// Rutina que prepara a los jugadores para la siguiente mano.
	protected void reiniciarMano() {
		
		for (JugadorBlackJack player : this.jugadores) {
			
			if (player.getDinero() >= this.apuestaMinima) {
				
				player.clearMano();
				player.setTodaviaNoJugo(true);
				player.noAposto();
				player.clearApuesta();
				
			}	
			else {
				
				this.eliminar(player);
				
			}
			
		}
		
		this.clearMano();
		
		
	}

	// Rutina que reparte las ganancias a los jugadores.
	public void definirGanadores() {
		
		// Falta caso Black Jack y empate.
		
		Comparativo comparacion;
		
		for (JugadorBlackJack player : this.jugadores) {
			
			comparacion = this.compararManos(player);
			
			if (comparacion.pierde()) {
				
				player.giveDinero(player.getApuesta().getGanancia());
				
			}
			else if (comparacion.empate()) {
				
				player.giveDinero(player.getApuesta().getMonto());
				
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

		JugadorBlackJack contenedor;
		
		player.yaJugo();
		contenedor = this.seleccionarJugador();
		this.notificar(Evento.TERMINOTURNO, player);
		
		if (contenedor != null) {
			
			this.repartir(contenedor);
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		

	}
	
	public void eliminar(JugadorBlackJack player) {

		for (JugadorBlackJack p : this.jugadores) {
			
			if (player == p) {
				
				this.jugadores.remove(player);
				
			}
				
		}

	}
	
	private Comparativo comparador(EstadoDeMano otraMano) {

		System.out.println(this.getEstadoDeMano() + " " + otraMano);
		
		Comparativo res = Comparativo.PEOR;
		boolean esPeor;
		
		switch ((EstadoManoBlackJack) this.getEstadoDeMano()) {
		
			case BLACKJACK:
				
				if (otraMano != EstadoManoBlackJack.BLACKJACK) {
					
					res = Comparativo.MEJOR;
					
				}
				else {
					
					res = Comparativo.IGUAL;
					
				}
				
			case MENORA21:
				
				esPeor = (otraMano == EstadoManoBlackJack.BLACKJACK) || (otraMano.getPuntos() > this.getEstadoDeMano().getPuntos());
				
				if (esPeor) {
					
					res = Comparativo.PEOR;
					
				}
				else if (otraMano.getPuntos() == this.getEstadoDeMano().getPuntos()) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.MEJOR;
					
				}
				
			case MAYORA21:
				
				if (otraMano == EstadoManoBlackJack.MAYORA21) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.PEOR;
					
				}
				
			case IGUALA21:
				
				esPeor = (otraMano == EstadoManoBlackJack.BLACKJACK);
				
				if (esPeor) {
					
					res = Comparativo.PEOR;
					
				}
				else if (otraMano == EstadoManoBlackJack.IGUALA21) {
					
					res = Comparativo.IGUAL;
					
				}
				else {
					
					res = Comparativo.MEJOR;
					
				}
				
				
		default:
			break;
		
		}
		
		return res;
	}
	
	public Comparativo compararManos(IJugador player) {
		
		Comparativo comparacion;
		
		comparacion = this.comparador(player.getEstadoDeMano());
		
		return comparacion;
		
	}
	
/*
	 * 
	 - Implementación observado
	 * 
*/
	
	
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

	public int nroDeJugadores() {
		
		int res = 0;
		
		res = this.jugadores.size();
		
		return res;
		
	}
	
	public boolean seguimosJugando() {
		
		return (this.nroDeJugadores() > 0);
		
	}
	
	
/*
	 * 
	 - Getters and Setters 
	 * 
*/
	
	
	public void setApuestaMinima(int montoMinimo) {
		
		this.apuestaMinima = montoMinimo;
		
	}

	public int getApuestaMinima() {
		
		return this.apuestaMinima;
		
	}
	
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
	
}

