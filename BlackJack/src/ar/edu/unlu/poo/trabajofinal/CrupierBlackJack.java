package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Queue;

import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import ar.edu.unlu.poo.misfunciones.Intencion;
import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;

public class CrupierBlackJack extends ObservableRemoto implements IPersona, IJugador, ICrupier {

	private ArrayList<JugadorBlackJack> jugadores;
	private Queue<JugadorBlackJack> enEspera;
	private int apuestaMinima;
	private FileManager fileManager = new FileManager();
	private Mazo mazo;
	private Mano manoActual;
	private boolean todaviaNoJugo;
	
	public CrupierBlackJack() {

		this.setJugadores();
		this.setApuestaMinima(100);
		this.setMazo();
		this.setManoActual();
		
		this.enEspera = new LinkedList<JugadorBlackJack>();

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
				
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.SIETE));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.CABALLERO));
				player.addCarta(new Carta(Palo.CORAZON, ContenidoDeCarta.CUATRO));
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
	public void addJugador(String nombre, int plata) throws RemoteException {
			
		JugadorBlackJack player;
		
		if (nombre == "") {
			
			nombre = "Player " + this.jugadores.size();
			
		}

		player = new JugadorBlackJack(nombre, plata);	
		
		if (this.jugadores.isEmpty()) {
			
			this.jugadores.add(player);
			this.notificarObservadores(Evento.PRIMERAPUESTA);
			
		}
		else if (this.nroDeJugadores() < 5) {

			this.enEspera.add(player);
			this.notificarObservadores(Notificacion.JUGADORCARGADO, player);
				
		}
		else {
				
			this.notificarObservadores(SaltoError.ERRORMAXJUGADORES, this);
				
		}

		
	}

	// Seteo de apuestas.
	@Override
	public void setApuestas(String monto) throws RemoteException {
		
		Apuesta apuesta = null;
		JugadorBlackJack player;
		int montoReal = 0;
		int montoNulo = -1;
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
						seteado = this.notificarObservadores(Notificacion.NOAPUESTA, player);
						
					}
					else if ((player.getDinero() < this.apuestaMinima)){
						
						try {
							this.eliminar(player);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else if (montoReal == montoNulo) {
						
						this.notificarObservadores(SaltoError.APOSTONONUMERO, player);
						
					}
					else if (!seteado) {
						
						if ((apuesta.getMonto() > player.getDinero()) && (montoReal != 0)) {
							
							seteado = this.notificarObservadores(SaltoError.ERRORFALTADEDINERO, player);
							
						}
						else if (montoReal < this.apuestaMinima) {
							
							seteado = this.notificarObservadores(SaltoError.ERRORAPUESTA, player);
							
						}
						else {
							
							player.setApuesta(apuesta);
							player.aposto();
							seteado = this.notificarObservadores(Notificacion.APUESTASETEADA, player);
						
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
		
		Mano mano = null;
		ArrayList<Carta> cartas;
		int tam;
		
		EstadoDeMano res = null;

		if (player != null) {
			
			try {
				mano = player.getManoActual();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
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
	public IJugador getApostador() throws RemoteException {
		
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
	public void repartir(JugadorBlackJack player)  throws RemoteException {
		
		// Repensar el sistema para repartir, corta.
		EstadoDeMano estatus = null;
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
			
			this.notificarObservadores(Evento.MOSTRARMANO, this);
			this.notificarObservadores(Evento.PREGUNTAROTRA, player);
			
		}
		else {
			
			try {
				cartita = this.darCarta();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.addCarta(cartita);
			player.mostrarCartas();
			
			estatus = this.checkEstadoJugador(player, player.getPuntaje());
			
			if (estatus == EstadoDeMano.MENORA21) {

				this.notificarObservadores(Evento.PREGUNTAROTRA, player);
			
			}
			else {
				
				terminar = true;
				
			}
			
		}

		if (terminar) {
			
			if (estatus == EstadoDeMano.BLACKJACK) {
				
				this.notificarObservadores(Notificacion.BLACKJACK, player);
				
			}
			
			try {
				this.terminarTurnoJugador(player);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	}
	
	// Rutina para que se de cartas el crupier.
	private void repartirCrupier() throws RemoteException {

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
			this.notificarObservadores(Evento.FINDEMANO);
			
		}
		else {
			
			this.repartirCrupier();
			
		}
		
	}

	// Rutina que prepara a los jugadores para la siguiente mano.
	@Override
	public boolean reiniciarMano() throws RemoteException {
		
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
				
				this.notificarObservadores(Evento.SINPLATA, player);
				eliminados.add(player);
				
			}
			
		}
		
		for (JugadorBlackJack eliminado : eliminados) {
			
			if (this.jugadores.size() == 1) {
				
				salir = true;
				
			}
			
			try {
				this.eliminar(eliminado);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		this.clearMano();
		
		return salir;
		
	}

	// Rutina que reparte las ganancias a los jugadores.
	private void definirGanadores() {
		
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
	private boolean primeraMano() {
		
		boolean res = false;
		
		for (Carta cartita : this.manoActual.getCartas()) {
			
			if (!cartita.esVisible()) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}
	
	@Override
	public void terminarTurnoJugador(JugadorBlackJack player) throws RemoteException {

		JugadorBlackJack contenedor;
		
		player.yaJugo();
		contenedor = this.seleccionarJugador();
		this.notificarObservadores(Evento.TERMINOTURNO, player);
		
		if (contenedor != null) {
			
			this.repartir(contenedor);
			
		}
		else {
			
			try {
				this.repartirCrupier();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}
	
	// Saca un jugador del ArrayList 'jugadores' (Podría dejarlos en un array de perdedores)
	@Override
	public void eliminar(JugadorBlackJack player) throws RemoteException {
		
		if (this.jugadores.size() == 1) {
			
			// Atado con alambre.
			this.jugadores.remove(player);
			this.clearMano();
			this.notificarObservadores(Evento.FINDELJUEGO);
			
		}
		else {
		
			for (JugadorBlackJack p : this.jugadores) {
				
				if (player == p) {
					
					this.jugadores.remove(player);
					
				}
					
			}
			
		}

		
	}
	
	private Comparativo comparador(IJugador player) throws RemoteException {

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
	
	private Comparativo compararManos(IJugador player) {
		
		Comparativo comparacion = null;
		
		try {
			comparacion = this.comparador(player);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		this.notificarObservadores(Evento.PRIMERAPUESTA);
		
	}
	
	private void eliminarTodo()  throws RemoteException {
		
		this.jugadores.clear();
		try {
			this.clearMano();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.notificarObservadores(Evento.FINDELJUEGO, this);
		
	}
	
	// Checkea si lo ingresado en Apuesta es un comando de salida.
	private boolean checkInput(String input) throws RemoteException {
		
		boolean respuesta = false;
		Intencion means = new Intencion();
		JugadorBlackJack player = null;
		
		try {
			player = (JugadorBlackJack) this.getApostador();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (means.out(input)) {
			
			respuesta = this.notificarObservadores(Evento.ADVERTENCIAGUARDADO, player);
			this.eliminarTodo();
			
			
		}
		else if (means.quit(input)) {
			
			try {
				this.setRanking(this.getApostador());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.notificarObservadores(Evento.TERMINOTURNO, player);
			try {
				this.eliminar(player);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (means.esoyam(input)) {
			
			player.giveDinero(1000);
			this.notificarObservadores(Evento.PRIMERAPUESTA, this);
			respuesta = true;
			
		}
		else if (means.help(input)) {
			
			this.notificarObservadores(Evento.HELP, this);
			respuesta = true;
			this.notificarObservadores(Evento.PRIMERAPUESTA, this);
			
			
		}
		
		return respuesta;
		
	}
	
/*
	 * 
	 - Implementación observado
	 * 
*/

	@Override
	public boolean notificarObservadores(IMensaje event, IJugador player) throws RemoteException {
		
		try {
			event.setRemitente(player.getNombre());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.notificarObservadores(event);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}

	//////////////////////////////////////////////////
	// Implementación  que tuve que agregar por RMI //
	//////////////////////////////////////////////////
	
	// - Crupier - //
	
	private void barajar() {
		
		this.mazo.barajar();
		
	};
	
	private void setMazo(Mazo mazo) {
		
		this.mazo = mazo;
		
	}

	private Mazo getMazo() {
		return mazo;
	}

	@Override
	public int getDinero() throws RemoteException {
	
		return 0;
	}

	@Override
	public String getNombre() throws RemoteException {
		
		return "Crupier";
	}

	// - JUGADOR - //
	
	@Override
	public String[] getCartas() throws RemoteException {
		
		int size = this.manoActual.getCartas().size();
		int contador = 0;
		String[] cartas = new String[size];
		
		for (Carta cartita : this.manoActual.getCartas()) {
			
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
	public String[] getIdCartas() throws RemoteException {
		
		int size = this.manoActual.getCartas().size();
		int contador = 0;
		String[] cartas = new String[size];
		
		for (Carta cartita : this.manoActual.getCartas()) {
			
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
	public boolean todaviaNoJugo() throws RemoteException {
		return todaviaNoJugo;
	}

	@Override
	public EstadoDeMano getEstadoDeMano() throws RemoteException {
		
		return this.manoActual.getEstado();
		
	}
	
	@Override
	public void mostrarCarta(int pos) throws RemoteException {
		
		this.manoActual.getCartas().get(pos).setVisibilidad(true);
		
	}
	
	@Override
	public void mostrarCarta() throws RemoteException {
		
		this.mostrarCarta(0);
		
	}
	
	@Override
	public void mostrarCartas() throws RemoteException {
		
		for (Carta carta : this.manoActual.getCartas()) {
			
			carta.setVisibilidad(true);
			
		}
		
	}

	@Override
	public int getNroCartas() throws RemoteException {
		
		return this.manoActual.getCartas().size();
		
	}
	
	@Override
	public void addCarta(Carta carta) throws RemoteException {
		
		this.manoActual.addCarta(carta);
		
	}
	
	@Override
	public void clearMano() throws RemoteException {
		
		this.manoActual.clear();
	
	}
	
	/////////////////////////
	// Getters and Setters //
	/////////////////////////
	
	private void setApuestaMinima(int montoMinimo) {
		
		this.apuestaMinima = montoMinimo;
		
	}

	@Override
	public int getApuestaMinima() throws RemoteException {
		
		return this.apuestaMinima;
		
	}
	
	private void setJugadores() {
		this.jugadores = new ArrayList<JugadorBlackJack>();
	}

	@Override
	public int getPuntaje() throws RemoteException {
		
		Mano mano = this.manoActual;
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
	
	private void setMazo() {
		
		MazoDeNaipes m = new MazoDeNaipes();
		this.setMazo(m);
		
	}
	
	private ArrayList<JugadorBlackJack> getJugadores() {
		return jugadores;
	}

	private int nroDeJugadores() {
		
		int res = 0;
		
		res = this.jugadores.size();
		
		return res;
		
	}
	
	private void setRanking(IJugador player) throws IOException {
		
		fileManager.addToRanking(player.getNombre(), (player.getDinero() - 1000));
		
	}

	@Override
	public ArrayList<String> getRanking() throws IOException, RemoteException {
		
		return this.fileManager.loadRanking();
		
	}

	@Override
	public ArrayList<String> getHelp() throws IOException, RemoteException {
		
		return this.fileManager.loadHelp();
		
	}

	@Override
	public Mano getManoActual() throws RemoteException {
		return manoActual;
	}

	private void setManoActual() {
		this.manoActual = new Mano();
	}

}

