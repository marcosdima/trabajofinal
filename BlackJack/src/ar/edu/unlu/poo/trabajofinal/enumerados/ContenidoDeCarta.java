package ar.edu.unlu.poo.trabajofinal.enumerados;

public enum ContenidoDeCarta {
	
	AS(1, "As"),
	DOS(2, "2"),
	TRES(3, "3"),
	CUATRO(4, "4"),
	CINCO(5, "5"),
	SEIS(6, "6"),
	SIETE(7, "7"),
	OCHO(8, "8"),
	NUEVE(9, "9"),
	DIEZ(10, "10"),
	CABALLERO(10, "Caballero"),
	REINA(10, "Reina"),
	REY(10, "Rey");
	
	private int valor;
	private String key;
	
	ContenidoDeCarta(int valor, String key) {
		
		this.valor = valor;
		this.key = key;
	}
	
	public int getValor() {
		
		return this.valor;
		
	}

	public String getLabel() {
		
		return this.key;
		
	}
	
}
