package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.GridLayout;

import ar.edu.unlu.poo.gui.Componente;

public class PanelGrilla extends PanelMenu {

	private int filas;
	private int columnas;
	
	public PanelGrilla(Componente[] botones, int filas, int columnas, int alto, int lados) {
		
		super(botones, alto, lados);
		this.filas = filas;
		this.columnas = columnas;
		this.setFormato();
		
	}
	
	public PanelGrilla(Componente[] botones, int filas, int columnas) {
		
		this (botones, filas, columnas, 0, 0);
		
	}
	
	public PanelGrilla(Componente[] botones) {
		
		this (botones, 1, 1, 0, 0);
		
	}

	
	@Override
	protected void setFormato() {
		
		PanelGrilla framecito = this;
		GridLayout grid = new GridLayout(this.filas, this.columnas, this.getEspacioAlto(), this.getEspacioLados());

		framecito.setLayout(grid);
		
		for (Componente comp : this.getComponentes()) {
			
			framecito.add(comp);
			
		}
		
		
	}

}
