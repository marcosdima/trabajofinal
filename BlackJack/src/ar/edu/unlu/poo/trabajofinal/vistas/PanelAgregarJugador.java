package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.InputText;
import ar.edu.unlu.poo.gui.Panel;

public class PanelAgregarJugador extends Panel {

	InputText inputNombre;
	Boton add;
	String nombre;
	
	public PanelAgregarJugador() {
		
		super();
		this.setInputNombre();
		this.setAdd();
		this.setPanel();
		
	}

	private void setInputNombre() {
		this.inputNombre = new InputText("Ingrese un nombre...");
	}
	
	private void setAdd() {
		
		this.add = new Boton("Agregar");
		this.add.evento(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String str = inputNombre.getText();
				
				if (str.length() < 10) {
					
					nombre = str;
					add.apagar();
					
				}
				
			}
			
			
			
		});
		
	}

	private void setPanel() {
		
		GridLayout grid = new GridLayout(2, 1, 10, 10);
		
		this.getPanel().setLayout(grid);
		
		this.getPanel().add(this.inputNombre.getComponent());
		this.getPanel().add(this.add.getComponent());
		
	}

	public Boton getAdd() {
		
		return this.add;
		
	}
	
	public String getNombre() {
		
		return this.nombre;
		
	}
	
}
