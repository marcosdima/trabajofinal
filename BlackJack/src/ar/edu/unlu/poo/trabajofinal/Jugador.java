package ar.edu.unlu.poo.trabajofinal;

import ar.edu.unlu.poo.trabajofinal.commons.Puntuable;

public abstract class Jugador implements Puntuable, IJugador, IPersona {

	private Mano manoActual;
	private boolean todaviaNoJugo;
	private String nombre;
	private int dinero;
	
	public Jugador(String nombre, int money) {
		
		this.setNombre(nombre);
		this.setDinero(money);
		this.setManoActual();
		
	}

	public void addCarta(Carta carta) {
		
		this.getManoActual().addCarta(carta);
		
	}
	
	public abstract int getPuntaje();

	public void clearMano() {
		
		this.getManoActual().clear();
	
	}
	
	public void mostrarCarta(int pos) {
		
		this.getManoActual().getCartas().get(pos).setVisibilidad(true);
		
	}
	
	public void mostrarCarta() {
		
		this.mostrarCarta(0);
		
	}
	
	public void mostrarCartas() {
		
		for (Carta carta : this.manoActual.getCartas()) {
			
			carta.setVisibilidad(true);
			
		}
		
	}

	public int getNroCartas() {
		
		return this.getManoActual().getCartas().size();
		
	}
	
	//Estos son de la mano.

	public Mano getManoActual() {
		return manoActual;
	}
	
	public void setManoActual() {
		
		this.manoActual = new Mano();
		
	} 
	
	public void setTodaviaNoJugo(boolean terminoTurno) {
		this.todaviaNoJugo = terminoTurno;
	}

	public void yaJugo() {
		
		this.setTodaviaNoJugo(false);
		
	}

	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void giveDinero(int monto) {
		
		this.dinero += monto;
		
	}
	
	/////////////////////////////
	// Implementación IJugador //
	/////////////////////////////
	
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
	
	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}

	public EstadoDeMano getEstadoDeMano() {
		
		return this.getManoActual().getEstado();
		
	}
	
}
