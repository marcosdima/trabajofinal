package ar.edu.unlu.poo.gui;

import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class Panel extends Componente {
	
	private JPanel panel;
	
	public Panel(Container panel) {
		
		super(panel);
		this.setPanel(panel);
		
	}

	public void setPanel(Container panel) {
		this.panel = (JPanel) panel;
	}



	public void setLayout(LayoutManager lymg) {
		
		this.panel.setLayout(lymg);
		
	}
	
	public void add(Componente comp) {
		
		this.panel.add(comp.getComponent());
		
	}
	
}
