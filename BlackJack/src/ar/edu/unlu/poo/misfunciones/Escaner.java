package ar.edu.unlu.poo.misfunciones;

import java.util.Scanner;

public class Escaner {

	private Scanner sc = new Scanner(System.in); 
	
	public boolean siONo() {
		
		boolean res = false;
		
		String input = this.next().toLowerCase();
		String[] yes = {"si", "s", "yes", "y", "sisi", "sis", "yyes"};
		//String[] no = {"no", "n", "nop", "nope", "nono", "nain", "nel"};
		
		for (String str : yes) {
			
			if (input.equals(str)) {
				
				res = true;
				
			}
			
		}
		
		return res;
		
	}

	public int nextInt() {
		
		int res = sc.nextInt();
		
		return res;
		
	}

	public String next() {
		
		String res = sc.next();
		
		return res;
		
	}

}
