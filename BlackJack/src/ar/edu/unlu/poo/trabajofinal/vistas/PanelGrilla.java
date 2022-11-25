package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.Component;
import java.awt.GridLayout;

public class PanelGrilla extends PanelMenu {

	private static final long serialVersionUID = 1L;
	private int filas;
	private int columnas;
	
	public PanelGrilla(Component[] botones, int filas, int columnas, int alto, int lados) {
		
		super(botones, alto, lados);
		this.filas = filas;
		this.columnas = columnas;
		this.setFormato();
		
	}
	
	public PanelGrilla(Component[] botones, int filas, int columnas) {
		
		this (botones, filas, columnas, 0, 0);
		
	}
	
	public PanelGrilla(Component[] botones) {
		
		this (botones, 1, 1, 0, 0);
		
	}

	@Override
	protected void setFormato() {
		
		PanelGrilla framecito = this;
		GridLayout grid = new GridLayout(this.filas, this.columnas, this.getEspacioAlto(), this.getEspacioLados());

		framecito.setLayout(grid);
		
		for (Component comp : this.getComponentes()) {
			
			framecito.add(comp);
			
		}
	
	}

}
