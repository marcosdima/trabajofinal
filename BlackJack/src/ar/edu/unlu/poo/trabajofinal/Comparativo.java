package ar.edu.unlu.poo.trabajofinal;

import java.io.Serializable;

public enum Comparativo implements Serializable {
	MEJOR, PEOR, IGUAL;
	
	public boolean gana() {
		
		return (this == MEJOR);
		
		
	}
	
	public boolean empate() {
		
		return (this == IGUAL);
		
		
	}
	
	public boolean pierde() {
		
		return (this == PEOR);

	}
	
	
}
