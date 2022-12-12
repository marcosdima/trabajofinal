package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface ICrupier extends IObservableRemoto {

	// Rutina que reparte la primera mano.
	void repartirPrimeraTanda() throws RemoteException;

	// Appendea un jugador al ArrayList de jugadores.
	void addJugador(String nombre, int plata) throws RemoteException;

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
	void repartir(JugadorBlackJack player)  throws RemoteException;

	// Rutina que prepara a los jugadores para la siguiente mano.
	boolean reiniciarMano() throws RemoteException;

	void terminarTurnoJugador(JugadorBlackJack player) throws RemoteException;

	// Saca un jugador del ArrayList 'jugadores' (Podría dejarlos en un array de perdedores)
	void eliminar(JugadorBlackJack player) throws RemoteException;

	// Genera los archivos de guardado.
	void guardado(String tag) throws IOException;

	void cargado(String tag) throws IOException;

	boolean notificarObservadores(IMensaje event, IJugador player) throws RemoteException;

	int getDinero() throws RemoteException;

	String getNombre() throws RemoteException;

	String[] getCartas() throws RemoteException;

	String[] getIdCartas() throws RemoteException;

	boolean todaviaNoJugo() throws RemoteException;

	EstadoDeMano getEstadoDeMano() throws RemoteException;

	void mostrarCarta(int pos) throws RemoteException;

	void mostrarCarta() throws RemoteException;

	void mostrarCartas() throws RemoteException;

	int getNroCartas() throws RemoteException;

	void addCarta(Carta carta) throws RemoteException;

	void clearMano() throws RemoteException;

	int getApuestaMinima() throws RemoteException;

	int getPuntaje() throws RemoteException;

	ArrayList<String> getRanking() throws IOException, RemoteException;

	ArrayList<String> getHelp() throws IOException, RemoteException;

	Mano getManoActual() throws RemoteException;

}