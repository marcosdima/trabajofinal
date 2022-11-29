package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.unlu.poo.gui.Boton;

public class ModuloAgregarJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField inputNombre;
	private Boton add;
	
	public ModuloAgregarJugador() {
		
		super();
		this.setAdd();
		this.setInput();
		this.setPanel();
		
	}

	private void setPanel() {
		
		ModuloAgregarJugador framecito = this;
	
		GridLayout grid = new GridLayout(2, 1, 1, 1);
		
		framecito.setLayout(grid);
		
		this.add(this.inputNombre);
		this.add(this.add);
		
	}

	public void apagar() {
		
		this.getAdd().setEnabled(false);
		this.getInputNombre().setEnabled(false);
		
	}
	
	private void setAdd() {
		
		this.add = new Boton("Agregar");
		
	}

	public Boton getAdd() {
		
		return this.add;
		
	}
	
	public JTextField getInputNombre() {
		return inputNombre;
	}

	private void setInput() {
		this.inputNombre = new JTextField("Ingresar nombre...");
	}

	public String getString() {
		
		return this.inputNombre.getText();
		
	}

	public String getText() {
		
		return this.getInputNombre().getText();
		
	}

}
