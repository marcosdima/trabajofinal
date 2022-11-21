package ar.edu.unlu.poo.gui;

import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class Panel extends Componente {
	
	private JPanel panel;
	
	public Panel(Container panel) {
		
		this.setPanel(panel);
		
	}

	public Panel() {

		this.panel = new JPanel();
		this.setComponent(panel);
		
	}
	
	public void setPanel(Container panel) {
		this.panel = (JPanel) panel;
	}

	public void setLayout(LayoutManager lay) {
				
		this.panel.setLayout(lay);
		
	}
	
	public void add(Componente comp) {
		
		this.panel.add(comp.getComponent());

	}
	
	public void add(Componente comp, String cardinal) {
		
		this.panel.add(comp.getComponent(), cardinal);
		
	}
	
	public JPanel getPanel() {
		
		return this.panel;
		
	}
}
