package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.Mensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;

public interface ICrupier {

	// Rutina que reparte la primera mano.
	void repartirPrimeraTanda() throws RemoteException;

	// Appendea un jugador al ArrayList de jugadores.
	void addJugador(String nombre) throws RemoteException;

	// Seteo de apuestas.
	void setApuestas(String monto) throws RemoteException;

	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	ArrayList<IJugador> getDatosJugadores() throws RemoteException;

	// Retorna una carta del mazo.
	Carta darCarta();

	// Devuelve un jugador disponible para jugar.
	JugadorBlackJack seleccionarJugador() throws RemoteException;

	// Devuelve un jugador disponible para apostar.
	IJugador getApostador() throws RemoteException;

	// Este metodo se encarga de repartir la carta a los JugadoresBlackJack.
	void repartir(JugadorBlackJack player);

	// Rutina para que se de cartas el crupier.
	void repartirCrupier();

	// Rutina que prepara a los jugadores para la siguiente mano.
	boolean reiniciarMano();

	// Rutina que reparte las ganancias a los jugadores.
	void definirGanadores();

	// Devuelve true si detecta que es la primera mano.
	boolean primeraMano();

	void terminarTurnoJugador(JugadorBlackJack player);

	// Saca un jugador del ArrayList 'jugadores' (Podría dejarlos en un array de perdedores)
	void eliminar(JugadorBlackJack player);

	Comparativo compararManos(IJugador player);

	// Genera los archivos de guardado.
	void guardado(String tag) throws IOException;

	void cargado(String tag) throws IOException;

	boolean notificar(Evento mensaje, IJugador data);

	boolean notificar(Evento mensaje, ArrayList<IJugador> actuDatos);

	boolean notificar(Evento mensaje);

	boolean notificar(SaltoError mensaje, IJugador data);

	boolean notificar(Notificacion mensaje, IJugador data);

	void adaptarNotificacion(Mensaje arg);

	void setApuestaMinima(int montoMinimo);

	int getApuestaMinima();

	void setJugadores(int n);

	int getPuntaje();

	ArrayList<JugadorBlackJack> getJugadores();

	int nroDeJugadores();

	ArrayList<String> getRanking() throws IOException;

	ArrayList<String> getHelp() throws IOException;

	int getDineroBase();

	void setDineroBase(int dineroBase);

	void barajar();

	void setMazo(Mazo mazo);

	void darCarta(Jugador player);

	Mazo getMazo();

	void addCarta(Carta carta);

	void clearMano();

	void mostrarCarta(int pos);

	void mostrarCarta();

	void mostrarCartas();

	int getNroCartas();

	Mano getManoActual();

	void setManoActual();

	void setTodaviaNoJugo(boolean terminoTurno);

	void yaJugo();

	String[] getCartas();

	String[] getIdCartas();

	boolean todaviaNoJugo();

	EstadoDeMano getEstadoDeMano();

	String getNombre();

	int getDinero();

}