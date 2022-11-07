package ar.edu.unlu.poo.trabajofinal;

public class DatosDeJugador {

	private String nombre;
	private String[] cartas;
	private String puntaje;
	private Jugador player;
	private boolean todaviaNoJugo;
	private boolean sigueJugando;
	
	public DatosDeJugador(Jugador player) {
		
		this.setPlayer(player);

	}
	
	public void setPlayer(Jugador player) {
		this.player = player;
		this.setDatos();
	}
	
	public void setDatos() {
		
		this.nombre = this.player.getNombre();
		this.setCartas();
		this.puntaje = Integer.toString(this.player.getPuntaje());
		this.todaviaNoJugo = player.todaviaNoJugo();
		this.sigueJugando = player.sigueJugando();
		
	}

	private void setCartas() {
		
		int size = this.player.getManoActual().getCartas().size();
		int contador = 0;
		String[] cartas = new String[size];
		
		for (Carta cartita : this.player.getManoActual().getCartas()) {
			
			if (cartita.esVisible()) {
				
				cartas[contador] = cartita.getDesc();
				
			}
			else {
				
				cartas[contador] = "Cubierta";
				
			}
			
			contador++;
			
		}
		
		this.cartas = cartas;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public String[] getCartas() {
		return cartas;
	}

	public String getPuntaje() {
		return puntaje;
	}

	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}
	
	public boolean sigueJugando() {
		
		return this.sigueJugando;
		
	}
	
}
