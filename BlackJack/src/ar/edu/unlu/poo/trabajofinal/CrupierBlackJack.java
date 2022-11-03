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

	public boolean puedeSeguirJugando(Jugador player) {
		
		boolean res = true;
		
		if (player.getPuntaje() >= 21) {
			
			res = false;
			
		}
		
		return res;
		
	}
	
	protected void setMazo() {
		
		MazoDeNaipes m = new MazoDeNaipes();
		this.setMazo(m);
		
	}
	
	public ArrayList<JugadorBlackJack> getJugadores() {
		return jugadores;
	}

	public void setJugadores(int n) {
		this.jugadores = new ArrayList<JugadorBlackJack>(n);
	}

	public void addJugador(String nombre, int plata) {
			
		boolean nombreNulo = (nombre == null);
		boolean noHayJugadores = this.jugadores.isEmpty();
		boolean seAgrego;
		
		if (!nombreNulo) {
			
			JugadorBlackJack player = new JugadorBlackJack(nombre, plata);
			
			seAgrego = this.jugadores.add(player);
			
			if (seAgrego) {
				
				player.aposto();
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
			
			this.notificar(Evento.PRIMERAREPARTIDA);
			
		}
		
		
	}

	public void terminarMano() {
		
		for (Jugador player : this.jugadores) {
			
			player.clearMano();
			
		}
		
		this.clearMano();
		
	}

	public void setApuestas(int apuestaMinima, int monto) {
		
		Apuesta apuesta = new Apuesta(monto);
		boolean seteado;
		
		for (JugadorBlackJack player : this.jugadores) { 
			
			seteado = false;
			
			// Hay que hacer que se setee en null siempre que se termine la mano.
			if (player.getApuesta() == null) {
				
				if (apuesta.getMonto() > player.getDinero()) {
					
					seteado = this.notificar(Error.ERRORFALTADEDINERO, player);
					
				}
				else if (monto < apuestaMinima) {
					
					seteado = this.notificar(Error.ERRORAPUESTA, apuesta);
					
				}
				else if (monto == 0) {
					
					player.noSigue();
					seteado = this.notificar(Evento.NOAPUESTA, player);
					
				}
				else {
					
					player.setApuesta(apuesta);
					seteado = this.notificar(Evento.APUESTASETEADA, player);
				
				}
		
			}
			
		if (!seteado) {
				
			this.notificar(Evento.JUGAR);
				
		}
			
		}
		
	}

	public void getDatosJugadores() {
		
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
		
		this.notificar(Evento.MOSTRARMANO, datosDeJugadores);
		
	}
	
	public Carta darCarta() {
		
		return this.getMazo().agarrarCarta();
		
	}
	
	public void repartir(boolean quiereMas) {
		
		Jugador contenedorJugador = this.seleccionarJugador();
		
		if (quiereMas) {
			
			if (contenedorJugador.getManoActual().getCartas().size() == 2) {
				
				contenedorJugador.mostrarCartas();
				this.checkEstadoJugador(contenedorJugador);
				this.notificar(Evento.PREGUNTAROTRA);
				
			}
			else {
				
				contenedorJugador.addCarta(this.darCarta());
				
				if (contenedorJugador.getPuntaje() == 21) {
					
					contenedorJugador.yaJugo();
					this.notificar(Evento.JUGAR);
					
				}
				
			}
			
			
				
		}
		else {
			
			contenedorJugador.yaJugo();
			this.notificar(Evento.JUGAR);
			
		}
		
		
	}
	
	private void checkEstadoJugador(Jugador contenedorJugador) {
		// TODO Auto-generated method stub
		
	}

	private Jugador seleccionarJugador() {
		
		boolean seteado = false;
		Jugador contenedorJugador = new JugadorBlackJack("Contenedor", 0);
		
		for (JugadorBlackJack player: this.jugadores) {
			
			if ((player.todaviaNoJugo()) && (!seteado)) {
				
				contenedorJugador = player;
				seteado = true;
				
			}
			
		}
		
		if ((!seteado) && (this.todaviaNoJugo())) {
			
			contenedorJugador = this;
			
		}
		else if (!seteado) {
			
			contenedorJugador = null;
			
		}
		
		return contenedorJugador;
		
	}
	
	public DatosDeJugador getApostador() {
		
		boolean seteado = false;
		DatosDeJugador dato = new DatosDeJugador(new JugadorBlackJack("Container", 0));
		
		for (JugadorBlackJack player : this.jugadores) {
			
			if (player.sigueJugando() && (!seteado)) {
				
				dato = new DatosDeJugador(player);
				seteado = true;
				
			}
			
		}
		
		return dato;
		
	}
	
	//////////////////////////////////
	// Implementación de Observado //
	//////////////////////////////////
	
	@Override
	public void agregarObservador(Observador observer) {
		
		this.observers.add(observer);
		
	}

	@Override
	public boolean notificar(IMensaje mensaje, JugadorBlackJack actuJugador) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje, actuJugador);
			
		}
		
		return true;
		
	}
	
	@Override
	public boolean notificar(IMensaje mensaje, Apuesta actuApuesta) {
		
		for (Observador observer: observers) {
			
			observer.actualizar(mensaje, actuApuesta);
			
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
