package ar.edu.unlu.poo.trabajofinal;

public class TestDeCosas {

	public static void main(String[] args) {
		
		
		CrupierBlackJack cj = new CrupierBlackJack(2);
		
		for (int i = 0; i < 2; i++) {
			
			cj.addJugador(("Player " + i), 1000);
			
		}
		
		cj.barajar();
		
		cj.repartirPrimeraMano();
		
		boolean jugar = true;
		
		while (jugar) {
			
			for (JugadorBlackJack player : cj.getJugadores()) {
				
				for (Carta c : player.getManoActual().getCartas()){
					
					System.out.println(c.getDesc());
					
				}
				
				System.out.println(player.getPuntaje());
				
			}
			
			jugar = false;
			
		}
	
	}

}
