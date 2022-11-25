package ar.edu.unlu.poo.gui;

import javax.swing.JOptionPane;

public class MessageDialogCustom {

	JOptionPane message;
	
	public static final int SI_O_NO = 0;
	public static final int OK = 1;
	public static final int INPUT = 2;
	public static final int ERROR = 3;
	
	private String titulo;
	private String descripcion;
	private int formato;
	
	public MessageDialogCustom(String titulo, String decripcion, int formato) {
		
		this.setTitulo(titulo);
		this.setFormato(formato);
		this.setDescripcion(decripcion);
		
	}
	
	public void showMessage() {
		
		this.message = new JOptionPane();
		
		switch(this.formato) {
		
		case SI_O_NO:
			
			JOptionPane.showConfirmDialog(message, "Si o no?", this.titulo, this.getFormato());
			
			
			
		
		
		}
		
	}

	public int getFormato() {
		return formato;
	}

	public void setFormato(int formato) {
		this.formato = formato;
	}

	
	public String getTitulo() {
		return titulo;
	}

	
	public void setTitulo(String desc) {
		this.titulo = desc;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
