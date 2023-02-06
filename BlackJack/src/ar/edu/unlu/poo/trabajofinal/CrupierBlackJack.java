package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import ar.edu.unlu.poo.misfunciones.Intencion;
import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.Mensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.Observado;
import ar.edu.unlu.poo.trabajofinal.commons.Observador;

public class CrupierBlackJack extends ObservableRemoto implements IJugador, Serializable, ICrupier {

	private static final long serialVersionUID = 1L;
	private ArrayList<JugadorBlackJack> jugadores;
	private int apuestaMinima;
	private FileManager fileManager = new FileManager();
	private int dineroBase = 1000;
	
	// Atributos que eran de Crupier.
	private Mazo mazo;
	
	// Atributos que eran de Jugador.
	private Mano manoActual;
	private boolean todaviaNoJugo;
	
	public CrupierBlackJack(int nroDeJugadores) {
	
		super();
		this.setMazo();
		this.setManoActual();
		this.setJugadores(nroDeJugadores);
		this.setApuestaMinima(100);

	}

	// Rutina que reparte la primera mano.
	@Override
	public void repartirPrimeraTanda() throws RemoteException {

		this.barajar();
		
		for (JugadorBlackJack player : this.getJugadores()) {
			
			if ((player.getNombre().startsWith("bj"))) {
				
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.CABALLERO));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.AS));
				player.mostrarCarta();
			}
			else if ((player.getNombre().toLowerCase().startsWith("pt"))) {
				
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.DOS));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.DOS));
				player.mostrarCarta();
				
			}
			else if ((player.getNombre().toLowerCase().startsWith("21"))) {
				
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.NUEVE));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.CABALLERO));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.DOS));
				player.mostrarCarta();
				
			}
			else {
			
				player.addCarta(this.darCarta());
				player.addCarta(this.darCarta());
				player.mostrarCarta();
			
			}
				
		}
		
		//this.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.CABALLERO));
		//this.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.AS));
		if (this.getCartas().length < 2){
			
			this.addCarta(this.darCarta());
			this.addCarta(this.darCarta());
			this.mostrarCarta();
			
		}
		
		
	}

	// Appendea un jugador al ArrayList de jugadores.
	@Override
	public void addJugador(String nombre) throws RemoteException {
			
		boolean nombreNulo = (nombre == null);
		boolean noHayJugadores = this.jugadores.isEmpty();
		boolean seguir = false;
		JugadorBlackJack player = new JugadorBlackJack(nombre, this.dineroBase);
		
		if (!nombreNulo) {
			
			if (this.nroDeJugadores() < 5) {

				this.jugadores.add(player);
				this.notificar(Notificacion.JUGADORCARGADO, player);
				
			}
			else {
				
				seguir = this.notificar(SaltoError.ERRORMAXJUGADORES, this);
				
			}
			
		}
		else if (noHayJugadores) {
			
			this.notificar(SaltoError.NOHAYJUGADORESCARGADOS, this);
			
		}
		else {
			
			seguir = true;
		
		}
		
		if (seguir) {
			
			this.repartirPrimeraTanda();
			this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			this.notificar(Evento.PRIMERAPUESTA);
			
		}
		
	}

	// Seteo de apuestas.
	@Override
	public void setApuestas(String monto) throws RemoteException {
		
		Apuesta apuesta = null;
		JugadorBlackJack player;
		int montoReal = 0;
		boolean seteado = false;
		boolean runAway = false;
		
		try {
			
			montoReal = Integer.valueOf(monto);
			apuesta = new Apuesta(montoReal);
			
		}
		catch(NumberFormatException e) {
		
			montoReal = -1;
			
		}
		
		runAway = this.checkInput(monto);
		player = (JugadorBlackJack) this.getApostador();
		
		if (!runAway && player != null) {
			
			try {
		
				// Si ya apostó, pasa al siguiente.
				if (player.todaviaNoAposto()) {
					
					if (montoReal == 0) {
						
						// Podría hacer que si ningún jugador apostó, saltee la mano.
						player.aposto();
						player.yaJugo();
						seteado = this.notificar(Notificacion.NOAPUESTA, player);
						
					}
					else if ((player.getDinero() < this.apuestaMinima)){
						
						this.eliminar(player);
						
					}
					else if (montoReal == -1) {
						
						this.notificar(SaltoError.APOSTONONUMERO, player);
						
					}
					else if (!seteado) {
						
						if ((apuesta.getMonto() > player.getDinero()) && (montoReal != 0)) {
							
							seteado = this.notificar(SaltoError.ERRORFALTADEDINERO, player);
							
						}
						else if (montoReal < this.apuestaMinima) {
							
							seteado = this.notificar(SaltoError.ERRORAPUESTA, player);
							
						}
						else {
							
							player.setApuesta(apuesta);
							player.aposto();
							seteado = this.notificar(Notificacion.APUESTASETEADA, player);
						
						}
		
					}
						
				}
				
			}
			catch(ConcurrentModificationException e) {
				

				
			}
			
		}
		
	}

	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	@Override
	public ArrayList<IJugador> getDatosJugadores() throws RemoteException {

		ArrayList<IJugador> datosDeJugadores = new ArrayList<IJugador>(this.jugadores.size() + 1);
		
		for (JugadorBlackJack player : this.jugadores) {

			datosDeJugadores.add(player);

		}
		
		// Por último, agrego al crupier para que quede en la última posición.
		datosDeJugadores.add(this);
		
		return datosDeJugadores;
		
	}
	
	// Retorna una carta del mazo.
	@Override
	public Carta darCarta() throws RemoteException {
		
		Carta cartita = this.getMazo().agarrarCarta();
		
		cartita.setVisibilidad(false);
		
		return cartita;
		
	}
	
	// Revisa el estado del jugador y devuelve un enumerado con el resultado.
	private EstadoDeMano checkEstadoJugador(IJugador player, int puntaje) {
		
		Mano mano;
		ArrayList<Carta> cartas;
		int tam;
		
		EstadoDeMano res = null;

		if (player != null) {
			
			mano = player.getManoActual();
			cartas = mano.getCartas();
			tam = cartas.size();

			if ((tam == 2) && (puntaje == 21)) {
				
				res = EstadoDeMano.BLACKJACK;
				
			}
			else if (puntaje < 21) {
				
				res = EstadoDeMano.MENORA21;
				
			}
			else if (puntaje > 21) {
				
				res = EstadoDeMano.MAYORA21;
				
			}
			else {
				
				res = EstadoDeMano.IGUALA21;
				
			}
			
			mano.setEstado(res);
			
		}
			
		return res;
		
	}

	// Devuelve un jugador disponible para jugar.
	@Override
	public JugadorBlackJack seleccionarJugador() throws RemoteException {
		
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
	@Override
	public IJugador getApostador() throws RemoteException{
		
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
	@Override
	public void repartir(JugadorBlackJack player) {
		
		// Repensar el sistema para repartir, corta.
		EstadoDeMano estatus;
		boolean primeraMano = false;
		boolean terminar = false;
		Carta cartita = null;
		
		// Si es la primera mano, muestra sus cartas y pone en true la variable 'primeraMano'.
		if (player.primeraMano()) {

			primeraMano = true;
			player.mostrarCartas();
			
		}
		
		estatus = this.checkEstadoJugador(player, player.getPuntaje());
		
		if ((primeraMano) && (estatus == EstadoDeMano.BLACKJACK)) {

			terminar = true;
			
		}
		else if (primeraMano) {
			
			try {
				this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.notificar(Evento.PREGUNTAROTRA, player);
			
		}
		else {
			
			try {
				cartita = this.darCarta();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			player.addCarta(cartita);
			player.mostrarCartas();
			
			estatus = this.checkEstadoJugador(player, player.getPuntaje());
			
			if (estatus == EstadoDeMano.MENORA21) {

				try {
					this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.notificar(Evento.PREGUNTAROTRA, player);
			
			}
			else {
				
				terminar = true;
				
			}
			
		}

		if (terminar) {
			
			if (estatus == EstadoDeMano.BLACKJACK) {
				
				this.notificar(Notificacion.BLACKJACK, player);
				
			}
			
			this.terminarTurnoJugador(player);
			
		}
			
	}
	
	// Rutina para que se de cartas el crupier.
	@Override
	public void repartirCrupier() {

		EstadoDeMano estado;
		Comparativo comparacion;
		int contadorDeGanadas = 0;
		// Este contador de empates podría implentarlo en un futuro.
		int mitad = 0;
		boolean terminar = false;
		
		if (this.primeraMano()) {
			
			this.mostrarCartas();
		
		}
		
		estado = this.checkEstadoJugador(this, this.getPuntaje());
		
		// Si su mano no supera ni iguala los 21, hay que decidir si toma otra carta o termina la mano.
		if (estado == EstadoDeMano.MENORA21) {
		
			for (JugadorBlackJack player : this.getJugadores()) {
				
				comparacion = this.compararManos(player);
				
				if (comparacion.gana()) {
					
					contadorDeGanadas++;
					
				}
				
			}
			
			 // Le sumo uno, contando al crupier. Esto porque cuando es un solo jugador la div = 0;
			mitad = Integer.divideUnsigned(this.nroDeJugadores() + 1, 2);
			
			if (contadorDeGanadas < mitad) {
				
				try {
					this.addCarta(this.darCarta());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}
			else {
				
				terminar = true;
				
			}
			
		}
		else {
			
			terminar = true;
			
		}
		
		if (terminar) {
			
			try {
				this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.notificar(Evento.FINDEMANO);
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		
	}

	// Rutina que prepara a los jugadores para la siguiente mano.
	public boolean reiniciarMano() {
		
		boolean salir = false;
		ArrayList<JugadorBlackJack> eliminados = new ArrayList<JugadorBlackJack>();

		for (JugadorBlackJack player : this.jugadores) {
			
			if (player.getDinero() >= this.apuestaMinima) {
				
				player.clearMano();
				player.setTodaviaNoJugo(true);
				player.noAposto();
				player.clearApuesta();
				
			}	
			else {
				
				this.notificar(Evento.SINPLATA, player);
				eliminados.add(player);
				
			}
			
		}
		
		for (JugadorBlackJack eliminado : eliminados) {
			
			if (this.jugadores.size() == 1) {
				
				salir = true;
				
			}
			
			this.eliminar(eliminado);
			
		}
		
		
		this.clearMano();
		
		return salir;
		
	}

	// Rutina que reparte las ganancias a los jugadores.
	@Override
	public void definirGanadores() {
		
		// Falta caso Black Jack.
		Comparativo comparacion;
		
		for (JugadorBlackJack player : this.jugadores) {
			
			comparacion = this.compararManos(player);
			
			if (comparacion.pierde()) {
				
				if (player.getEstadoDeMano() == EstadoDeMano.BLACKJACK) {
					
					player.giveDinero(player.getApuesta().getGanancia(true));
					
				}
				else {
					
					player.giveDinero(player.getApuesta().getGanancia(false));
				
				}
				
			}
			else if (comparacion.empate()) {
				
				player.giveDinero(player.getApuesta().getMonto());
				
			}
			
		}
		
	}

	// Devuelve true si detecta que es la primera mano.
	@Override
	public boolean primeraMano() {
		
		boolean res = false;
		
		for (Carta cartita : this.getManoActual().getCartas()) {
			
			if (!cartita.esVisible()) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}
	
	@Override
	public void terminarTurnoJugador(JugadorBlackJack player) {

		JugadorBlackJack contenedor = null;
		
		player.yaJugo();
		try {
			contenedor = this.seleccionarJugador();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.notificar(Evento.TERMINOTURNO, player);
		
		if (contenedor != null) {
			
			this.repartir(contenedor);
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		

	}
	
	// Saca un jugador del ArrayList 'jugadores' (Podría dejarlos en un array de perdedores)
	@Override
	public void eliminar(JugadorBlackJack player) {
		
		if (this.jugadores.size() == 1) {
			
			// Atado con alambre.
			this.jugadores.remove(player);
			this.clearMano();
			this.notificar(Evento.FINDELJUEGO);
			
		}
		else {
		
			for (JugadorBlackJack p : this.jugadores) {
				
				if (player == p) {
					
					this.jugadores.remove(player);
					
				}
					
			}
			
		}

		
	}
	
	private Comparativo comparador(IJugador player) {

		EstadoDeMano laMano = this.getEstadoDeMano();
		EstadoDeMano otraMano = player.getEstadoDeMano();
		Comparativo comparador = Comparativo.PEOR;
		
		if (laMano == EstadoDeMano.MAYORA21) {
			
			comparador = Comparativo.PEOR;
			
		}
		else if (otraMano != EstadoDeMano.MAYORA21) {
			
			if (laMano == EstadoDeMano.BLACKJACK) {
				
				if (otraMano == EstadoDeMano.BLACKJACK) {
					
					comparador = Comparativo.IGUAL;
					
				}
				else {
					
					comparador = Comparativo.MEJOR;
					
				}
				
			}
			else if (otraMano == EstadoDeMano.BLACKJACK) {
				
				comparador = Comparativo.PEOR;
				
			}
			else if (this.getPuntaje() == player.getPuntaje()) {
				
				comparador = Comparativo.IGUAL;
				
			}
			else if (this.getPuntaje() < player.getPuntaje()) {
				
				comparador = Comparativo.PEOR;
				
			}
			else {
				
				comparador = Comparativo.MEJOR;
				
			}
			
		}
		else {
			
			comparador = Comparativo.MEJOR;
			
		}
	
		return comparador;
		
	}
	
	@Override
	public Comparativo compararManos(IJugador player) {
		
		Comparativo comparacion;
		
		comparacion = this.comparador(player);
		
		return comparacion;
		
	}
	
	// Genera los archivos de guardado.
	@Override
	public void guardado(String tag) throws IOException {
		
		ArrayList<String> guardado = new ArrayList<String>();
		
		for (IJugador player : this.getDatosJugadores()) {
			
			guardado.add(player.getNombre() + "," + (player.getDinero()));
			
		}		
		
		this.fileManager.save(tag, guardado);
		
	}
	
	@Override
	public void cargado(String tag) throws IOException {
		
		JugadorBlackJack jugador;
		ArrayList<String> guardado = this.fileManager.load(tag);
		String[] array;
		String nombre = "";
		int dinero = 0;
		int largo = guardado.size() - 1;
		
		this.jugadores = new ArrayList<JugadorBlackJack>();
		
		for (int i = 0; i < largo; i++) {
			
			array = guardado.get(i).split(",");
			nombre = array[0].trim();
			dinero = Integer.valueOf(array[1].trim());
			
			jugador = new JugadorBlackJack(nombre, dinero);
			this.jugadores.add(jugador);
			
		}
		
		this.repartirPrimeraTanda();
		this.notificar(Evento.MOSTRARMANO, this.getDatosJugadores());
		this.notificar(Evento.PRIMERAPUESTA);
		
	}
	
	private void eliminarTodo() {
		
		this.jugadores.clear();
		this.clearMano();
		this.notificar(Evento.FINDELJUEGO);
		
	}
	
	// Checkea si lo ingresado en Apuesta es un comando de salida.
	private boolean checkInput(String input) {
		
		boolean respuesta = false;
		Intencion means = new Intencion();
		JugadorBlackJack player = null; 
		try {
			player =(JugadorBlackJack) this.getApostador();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (means.out(input)) {
			
			respuesta = this.notificar(Evento.ADVERTENCIAGUARDADO, player);
			this.eliminarTodo();
			
			
		}
		else if (means.quit(input)) {
			
			try {
				this.setRanking(this.getApostador());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.notificar(Evento.TERMINOTURNO, player);
			this.eliminar(player);
			
		}
		else if (means.esoyam(input)) {
			
			player.giveDinero(1000);
			respuesta = this.notificar(Evento.PRIMERAPUESTA);
			
		}
		else if (means.help(input)) {
			
			respuesta = this.notificar(Evento.HELP);
			this.notificar(Evento.PRIMERAPUESTA);
			
			
		}
		
		return respuesta;
		
	}
	
/*
	 * 
	 - Implementación observado
	 * 
*/
	
	@Override
	public boolean notificar(Evento mensaje, IJugador data) {
		
		Mensaje msj = new Mensaje(mensaje, data);
		this.adaptarNotificacion(msj);
		return true;
		
	}
	
	@Override
	public boolean notificar(Evento mensaje, ArrayList<IJugador> actuDatos) {
		
		Mensaje msj = new Mensaje(mensaje, actuDatos);
		this.adaptarNotificacion(msj);
		return true;
		
	}

	@Override
	public boolean notificar(Evento mensaje) {
		
		Mensaje msj = new Mensaje(mensaje, null);
		this.adaptarNotificacion(msj);
		return true;
		
	}

	@Override
	public boolean notificar(SaltoError mensaje, IJugador data) {
		
		Mensaje msj = new Mensaje(mensaje, data);
		this.adaptarNotificacion(msj);
		return true;

	}
	
	@Override
	public boolean notificar(Notificacion mensaje, IJugador data) {
		
		Mensaje msj = new Mensaje(mensaje, data);
		this.adaptarNotificacion(msj);
		return true;
		
	}
	
	@Override
	public void adaptarNotificacion(Mensaje arg) {
		try {
			this.notificarObservadores(arg);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
/*
	 * 
	 - Getters and Setters 
	 * 
*/
	
	
	@Override
	public void setApuestaMinima(int montoMinimo) {
		
		this.apuestaMinima = montoMinimo;
		
	}

	@Override
	public int getApuestaMinima() {
		
		return this.apuestaMinima;
		
	}
	
	@Override
	public void setJugadores(int n) {
		this.jugadores = new ArrayList<JugadorBlackJack>(n);
	}

	@Override
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
	
	@Override
	public ArrayList<JugadorBlackJack> getJugadores() {
		return jugadores;
	}

	@Override
	public int nroDeJugadores() {
		
		int res = 0;
		
		res = this.jugadores.size();
		
		return res;
		
	}
	
	private void setRanking(IJugador player) throws IOException {
		
		fileManager.addToRanking(player.getNombre(), (player.getDinero() - 1000));
		
	}

	@Override
	public ArrayList<String> getRanking() throws IOException {
		
		return this.fileManager.loadRanking();
		
	}

	@Override
	public ArrayList<String> getHelp() throws IOException {
		
		return this.fileManager.loadHelp();
		
	}

	@Override
	public int getDineroBase() {
		return dineroBase;
	}

	@Override
	public void setDineroBase(int dineroBase) {
		this.dineroBase = dineroBase;
	}

	// Métodos que eran de crupier.
	
	@Override
	public void barajar() {
		
		this.mazo.barajar();
		
	};
	
	@Override
	public void setMazo(Mazo mazo) {
		
		this.mazo = mazo;
		
	}
	
	@Override
	public void darCarta(Jugador player) {
		
		Carta cartita = this.mazo.agarrarCarta();
		player.addCarta(cartita);
		
	}

	@Override
	public Mazo getMazo() {
		return mazo;
	}
	
	// Métodos que eran de Jugador.
	
	@Override
	public void addCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}
	
	@Override
	public void clearMano() {
		
		this.getManoActual().clear();
	
	}
	
	@Override
	public void mostrarCarta(int pos) {
		
		this.getManoActual().getCartas().get(pos).setVisibilidad(true);
		
	}
	
	@Override
	public void mostrarCarta() {
		
		this.mostrarCarta(0);
		
	}
	
	@Override
	public void mostrarCartas() {
		
		for (Carta carta : this.manoActual.getCartas()) {
			
			carta.setVisibilidad(true);
			
		}
		
	}

	@Override
	public int getNroCartas() {
		
		return this.getManoActual().getCartas().size();
		
	}
	
	//Estos son de la mano.

	@Override
	public Mano getManoActual() {
		return manoActual;
	}
	
	@Override
	public void setManoActual() {
		
		this.manoActual = new Mano();
		
	} 
	
	public void setTodaviaNoJugo(boolean terminoTurno) {
		this.todaviaNoJugo = terminoTurno;
	}

	@Override
	public void yaJugo() {
		
		this.setTodaviaNoJugo(false);
		
	}

	/////////////////////////////
	// Implementación IJugador //
	/////////////////////////////

	@Override
	public String[] getCartas() {
		
		int size = this.getManoActual().getCartas().size();
		int contador = 0;
		String[] cartas = new String[size];
		
		for (Carta cartita : this.getManoActual().getCartas()) {
			
			if (cartita.esVisible()) {
				
				cartas[contador] = cartita.getDesc();
				
			}
			else {
				
				cartas[contador] = "Cubierta";
				
			}
			
			contador++;
			
		}
		
		return cartas;
		
	}
	
	@Override
	public String[] getIdCartas() {
		
		int size = this.getManoActual().getCartas().size();
		int contador = 0;
		String[] cartas = new String[size];
		
		for (Carta cartita : this.getManoActual().getCartas()) {
			
			if (cartita.esVisible()) {
				
				cartas[contador] = cartita.getIdentificador();
				
			}
			else {
				
				cartas[contador] = "CUBIERTA";
				
			}
			
			contador++;
			
		}
		
		return cartas;
		
	}
	
	@Override
	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}

	@Override
	public EstadoDeMano getEstadoDeMano() {
		
		return this.getManoActual().getEstado();
		
	}

	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Crupier";
	}

	@Override
	public int getDinero() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

