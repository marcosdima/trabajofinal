package ar.edu.unlu.poo.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	public static final int FLOW = 0;

	private static final long serialVersionUID = 1L;

	public Panel() {
		
		super();
		
	}
	
	public Panel(int layout) {
		
		super();
		
		switch(layout) {
		
		case FLOW:
			
			this.setLayout(new FlowLayout());
		
		}
		
		
	}
	
	public void addVacio() {
		
		this.add(new JLabel());

	}
	
}
