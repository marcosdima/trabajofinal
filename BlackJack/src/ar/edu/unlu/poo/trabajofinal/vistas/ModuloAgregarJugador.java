package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.InputText;
import ar.edu.unlu.poo.gui.Panel;

public class ModuloAgregarJugador extends Panel {

	private InputText inputNombre;
	private Boton add;
	private String id;
	
	public ModuloAgregarJugador(String id) {
		
		super();
		this.setInputNombre();
		this.setAdd();
		this.setPanel();
		this.id = id;
		
	}

	private void setInputNombre() {
		this.inputNombre = new InputText("Ingrese un nombre...");
	}
	
	private void setAdd() {
		
		this.add = new Boton("Agregar");
		
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
	
	public String getString() {
		
		return this.inputNombre.getText();
		
	}

	public void evento(ActionListener act) {
		
		this.getAdd().evento(act);
		
	}

	public void apagar() {
		
		this.getAdd().apagar();
		this.inputNombre.apagar();
		
	}
	
	public String getText() {
		
		return this.id;
		
	}
	
}
