package ar.edu.unlu.poo.trabajofinal;

public class JugadorBlackJack extends Jugador {
	
	private Apuesta apuesta;
	private boolean todaviaNoAposto;
	private boolean perdioLaMano;
	
 	public JugadorBlackJack(String nombre, int money) {
		
		super(nombre, money);
		this.setTodaviaNoJugo(true);
		this.clearApuesta();
		this.noAposto();
		this.noPerdioLaMano();

	}

	private void noPerdioLaMano() {
		
		this.perdioLaMano = false;
		
	}

	public void perdioLaMano() {
		
		this.perdioLaMano = true;
		
	}

	public boolean estadoMano() {
		
		return this.perdioLaMano;
		
	}
	
	public boolean todaviaNoAposto() {
		
		return this.todaviaNoAposto;
		
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
	
	public boolean primeraMano() {
		
		boolean res = false;
		
		for (Carta cartita : this.getManoActual().getCartas()) {
			
			if (!cartita.esVisible()) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}
	
	////////////////////////////
	// Funciones con Apuestas //
	////////////////////////////
	
	public Apuesta getApuesta() {
		return apuesta;
	}
	
	public void setApuesta(Apuesta apuesta) {
		
		if (apuesta.getMonto() <= this.getDinero()) {
			
			this.giveDinero(-1 * apuesta.getMonto());
			this.apuesta = apuesta;
			
		}
		
		
	}
	
	public void aposto() {
		
		this.todaviaNoAposto = false;
		
	}
	
	public void noAposto() {
		
		this.todaviaNoAposto = true;
		
	}

	public void clearApuesta() {
		
		Apuesta vacio = new Apuesta();
		
		this.apuesta = vacio;
		
	}

}
