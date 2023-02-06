package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.Mensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface ICrupier extends IObservableRemoto {

	// Rutina que reparte la primera mano.
	void repartirPrimeraTanda() throws RemoteException;

	// Appendea un jugador al ArrayList de jugadores.
	void addJugador(String nombre) throws RemoteException;

	// Seteo de apuestas.
	void setApuestas(String monto) throws RemoteException;

	// Devuelve un arrray list con datos de los jugadores (Incluyendo al crupier).
	ArrayList<IJugador> getDatosJugadores() throws RemoteException;

	// Retorna una carta del mazo.
	Carta darCarta() throws RemoteException;

	// Devuelve un jugador disponible para jugar.
	JugadorBlackJack seleccionarJugador() throws RemoteException;

	// Devuelve un jugador disponible para apostar.
	IJugador getApostador() throws RemoteException;

	// Este metodo se encarga de repartir la carta a los JugadoresBlackJack.
	void repartir(JugadorBlackJack player) throws RemoteException;

	// Rutina para que se de cartas el crupier.
	void repartirCrupier() throws RemoteException;

	// Rutina que prepara a los jugadores para la siguiente mano.
	boolean reiniciarMano() throws RemoteException;

	// Rutina que reparte las ganancias a los jugadores.
	void definirGanadores() throws RemoteException;

	// Devuelve true si detecta que es la primera mano.
	boolean primeraMano() throws RemoteException;

	void terminarTurnoJugador(JugadorBlackJack player) throws RemoteException;

	// Saca un jugador del ArrayList 'jugadores' (Podría dejarlos en un array de perdedores)
	void eliminar(JugadorBlackJack player) throws RemoteException;

	Comparativo compararManos(IJugador player) throws RemoteException;

	// Genera los archivos de guardado.
	void guardado(String tag) throws IOException;

	void cargado(String tag) throws IOException;

	boolean notificar(Evento mensaje, IJugador data) throws RemoteException;

	boolean notificar(Evento mensaje, ArrayList<IJugador> actuDatos) throws RemoteException;

	boolean notificar(Evento mensaje) throws RemoteException;

	boolean notificar(SaltoError mensaje, IJugador data) throws RemoteException;

	boolean notificar(Notificacion mensaje, IJugador data) throws RemoteException;

	void adaptarNotificacion(Mensaje arg) throws RemoteException;

	void setApuestaMinima(int montoMinimo) throws RemoteException; 

	int getApuestaMinima() throws RemoteException;

	void setJugadores(int n) throws RemoteException;

	int getPuntaje() throws RemoteException;

	ArrayList<JugadorBlackJack> getJugadores() throws RemoteException;

	int nroDeJugadores() throws RemoteException;

	ArrayList<String> getRanking() throws IOException;

	ArrayList<String> getHelp() throws IOException;

	int getDineroBase() throws RemoteException;

	void setDineroBase(int dineroBase) throws RemoteException;

	void barajar() throws RemoteException;

	void setMazo(Mazo mazo) throws RemoteException;

	void darCarta(Jugador player) throws RemoteException;

	Mazo getMazo() throws RemoteException;

	void addCarta(Carta carta) throws RemoteException;

	void clearMano() throws RemoteException;

	void mostrarCarta(int pos) throws RemoteException;
 
	void mostrarCarta() throws RemoteException;

	void mostrarCartas() throws RemoteException;

	int getNroCartas() throws RemoteException;

	Mano getManoActual() throws RemoteException;

	void setManoActual() throws RemoteException;

	void yaJugo() throws RemoteException;

	String[] getCartas() throws RemoteException;

	String[] getIdCartas() throws RemoteException;

	boolean todaviaNoJugo() throws RemoteException;

	EstadoDeMano getEstadoDeMano() throws RemoteException;

}