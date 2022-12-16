package ar.edu.unlu.poo.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageManager {
	
	private String source;
	private String folderCarta;
	
	public ImageManager(String direccion, String folderCarta) {
		
		this.setSource(direccion);
		this.setFolderCarta(folderCarta);

	}

	public void setSource(String source) {
		this.source = source;
	}
	
	

	public void setFolderCarta(String folderCarta) {
		this.folderCarta = folderCarta;
	}

	public ImageIcon imagen(String tag, int ancho, int largo) {
		
		String direccion = this.source + "/" + tag + ".png";
		Image img = new ImageIcon(direccion).getImage();
		ImageIcon img2 =new ImageIcon(img.getScaledInstance(ancho, ancho, Image.SCALE_SMOOTH));
		//Alto: 84 Ancho: 62

		return img2;
		
	}

	public JLabel imagenCarta(String tag) {
		
		String direccion = (this.source + "/Cartas/" + folderCarta + "/" + tag + ".png");
		Image img = new ImageIcon(direccion).getImage();
		ImageIcon img2 =new ImageIcon(img.getScaledInstance(62, 84, Image.SCALE_SMOOTH));
		//Alto: 84 Ancho: 62
		JLabel label = new JLabel(img2);

		return label;
		
	}
	
}
