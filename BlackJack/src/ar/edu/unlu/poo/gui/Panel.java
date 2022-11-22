package ar.edu.unlu.poo.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Panel extends Componente {
	
	private JPanel panel;
	private static int nroDePaneles = 0;
	private int id;
	
	public Panel(Container panel) {
		
		this.setPanel(panel);
		this.setLayout(new BorderLayout());
		this.setId();
		Panel.nroDePaneles++;
		
	}

	public Panel() {

		this.panel = new JPanel();
		this.setComponent(panel);
		this.setId();
		Panel.nroDePaneles++;
		
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

	public void clear() {
		
		this.panel.removeAll();
		
	}

	public int getNroDePaneles() {
		
		return Panel.nroDePaneles;
		
	}
	
	private void setId() {
		
		this.id = Panel.nroDePaneles;
		
	}
	
	public int getId() {
		
		return this.id;
		
	}

	@Override
	public String getText() {

		return "";
	}

	@Override
	public void evento(ActionListener action) {
		// TODO Auto-generated method stub
		
	}

	
	
}
