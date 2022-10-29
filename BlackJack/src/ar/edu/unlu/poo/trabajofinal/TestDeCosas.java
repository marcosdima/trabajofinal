package ar.edu.unlu.poo.trabajofinal;

import java.util.ArrayList;
import java.util.Scanner;


public class TestDeCosas {

	public static void main(String[] args) {
		
		ArrayList<JugadorBlackJack> pys = new ArrayList<JugadorBlackJack>(4);
		
		for (int i = 0; i < 1; i++) {
			
			JugadorBlackJack py = new JugadorBlackJack(("Player " + Integer.toString(i)), 1000);
			pys.add(py);
			
		}
		
		
		CrupierBlackJack cj = new CrupierBlackJack(pys);
		
		cj.barajar();
		
		cj.repartirPrimeraMano();
		
		boolean jugar = true;
		Scanner sc = new Scanner(System.in);
		
		while (jugar) {
			
			for (JugadorBlackJack player : pys) {
				
				for (Carta c : player.getManoActual().getCartas()){
					
					System.out.println(c.getDesc());
					
				}
				
				System.out.println(player.getPuntaje());
				
			}
			
			jugar = false;
			
		}
	
	}

}
