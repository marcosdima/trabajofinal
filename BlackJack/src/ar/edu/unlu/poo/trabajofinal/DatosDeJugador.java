package ar.edu.unlu.poo.trabajofinal;

public class DatosDeJugador {

	private String nombre;
	private String cartas;
	private String puntaje;
	private Jugador player;
	private boolean todaviaNoJugo;
	
	public DatosDeJugador(Jugador player) {
		
		this.setPlayer(player);
		this.setDatos();

	}
	
	public void setPlayer(Jugador player) {
		this.player = player;
	}
	
	public void setDatos() {
		
		this.nombre = this.player.getNombre();
		this.setCartas();
		this.puntaje = Integer.toString(this.player.getPuntaje());
		this.todaviaNoJugo = player.todaviaNoJugo();
		
	}

	
	private void setCartas() {
		
		String cartas = "";
		
		for (Carta cartita : this.player.getManoActual().getCartas()) {
			
			if (cartita.esVisible()) {
				
				cartas += cartita.getDesc() + '\r' + '\n';
				
			}
			else {
				
				cartas += "🂠";
				
			}
			
		}
		
		this.cartas = cartas;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	
	public String getCartas() {
		return cartas;
	}

	
	public String getPuntaje() {
		return puntaje;
	}

	
	public boolean todaviaNoJugo() {
		return todaviaNoJugo;
	}
	
	
	
}
