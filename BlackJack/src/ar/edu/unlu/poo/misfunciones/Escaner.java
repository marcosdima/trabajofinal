package ar.edu.unlu.poo.misfunciones;

import java.util.Scanner;

public class Escaner {

	private Scanner sc = new Scanner(System.in); 
	
	public boolean siONo() {
		
		boolean res = false;
		Intencion is = new Intencion();
		
		String input = this.next().toLowerCase();
		res = is.positive(input);
		
		return res;
		
	}

	public int nextInt() {
		
		int res = 0;
		String contenedor = sc.next();
		
		try {
			
			res = Integer.valueOf(contenedor);
			
		}
		catch(NumberFormatException e) {
			
			res = 0;
			
		}
			
		return res;
		
	}

	public String next() {
		
		String res = sc.next();
		
		return res;
		
	}

}
