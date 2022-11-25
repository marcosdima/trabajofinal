package ar.edu.unlu.poo.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageManager {
	
	private String source;
	private String folder;
	
	public ImageManager(String direccion, String folder) {
		
		this.setSource(direccion);
		this.setFolder(folder);
		
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public JLabel imagen(String tag) {
		
		String direccion = this.source + this.folder + "/" + tag + ".png";
		JLabel label = new JLabel(new ImageIcon(direccion));
		return label;
		
	}

}
