package ar.edu.unlu.poo.trabajofinal.enumerados;

import ar.edu.unlu.poo.trabajofinal.commons.IOpciones;

public enum OpcionesMenuPrincipal implements IOpciones{
	
	JUGAR(1, "Jugar"),
	CONFIGURACION(2, "Configuración"),
	SALIR(3, "Salir");
	
	private int id;
	private String label;
	
	OpcionesMenuPrincipal(int id, String label) {
		
		this.id = id;
		this.label = label;
		
	}

	
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	@Override
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

	
}
