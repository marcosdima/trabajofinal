package ar.edu.unlu.poo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	public static final int FLOW = 0;
	public static final int BORDER = 1;

	private static final long serialVersionUID = 1L;

	public Panel() {
		
		super();

	}
	
	public Panel(int layout) {
		
		super();
		
		switch(layout) {
		
		case FLOW:
			
			this.setLayout(new FlowLayout());
		
		
		case BORDER: 
			
			this.setLayout(new BorderLayout());
			
			
		}
		
		
	}
	
	public void addVacio() {
		
		this.add(new JLabel());

	}
	
	public void addVacio(String pos) {
		
		this.add(new JLabel(), pos);
		
	}
	
	public void setBack(int r, int g, int b) {
		
		Color color = new Color(r, g, b);
		this.setBackground(color);
		
	}
	
	
}
