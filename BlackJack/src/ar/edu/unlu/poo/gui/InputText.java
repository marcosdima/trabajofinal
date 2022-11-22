package ar.edu.unlu.poo.gui;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class InputText extends Componente {
	
	JTextField caja;
	
	public InputText(String str) {
		
		this.caja = new JTextField(str);
		this.setComponent(caja);

	}
	
	public InputText() {
		
		this("");
		
	}
	
	public void evento(ActionListener act) {
		
		this.caja.addActionListener(act);
		
	}
	
	public String getText() {
		
		return this.caja.getText();
		
	}
	
}
