package ar.edu.unlu.poo.trabajofinal.commons;

public enum OpcionesMenuPrincipal implements IOpciones {
	
	JUGAR(1, "Jugar"),
	CARGAR(2, "Cargar partida"),
	CONFIGURACION(3, "Configuración"),
	RANKING(4, "Ranking"),
	SALIR(5, "Salir");
	
	private int id;
	private String label;
	private String titulo;
	
 	OpcionesMenuPrincipal(int id, String label) {
		
		this.id = id;
		this.label = label;
		this.titulo = "Menu Principal";
		
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String[] getOpciones() {
		
		String[] arreglo = new String[(OpcionesMenuPrincipal.values().length)];
		int i = 0;
		
		for (OpcionesMenuPrincipal op : OpcionesMenuPrincipal.values()) {
			
			String linea = op.getId() + "- " + op.getLabel(); 
			arreglo[i] = linea;
			i++;
			
		}
		
		return arreglo;
	}
	
	public int size() {
		
		return (OpcionesMenuPrincipal.values().length);
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
	