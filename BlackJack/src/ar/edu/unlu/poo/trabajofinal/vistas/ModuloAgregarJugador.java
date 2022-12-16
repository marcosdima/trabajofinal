package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
	
		GridLayout grid = new GridLayout(2, 1, 10, 10);
		
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
		
		String frase = "Ingresar nombre...";
		this.inputNombre = new JTextField(frase);
		this.inputNombre.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			
				if (inputNombre.getText().equals(frase)) {
					
					inputNombre.setText("");
					
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
					
			}});
			
			

	}
		


	public String getString() {
		
		return this.inputNombre.getText();
		
	}

	public String getText() {
		
		return this.getInputNombre().getText();
		
	}

}
