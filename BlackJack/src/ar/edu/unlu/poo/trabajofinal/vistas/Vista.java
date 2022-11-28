package ar.edu.unlu.poo.trabajofinal.vistas;

public abstract class Vista implements IVista {
	
	protected boolean activa = false;

	public void setActiva(boolean actividad) {
		
		this.activa = actividad;
		
	};
	
	public boolean isActiva() {
		
		return activa;
		
	};
	
	public boolean getActiva() {
		
		return this.activa;
		
	}

}
