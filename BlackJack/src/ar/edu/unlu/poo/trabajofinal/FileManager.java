package ar.edu.unlu.poo.trabajofinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	
	private final String RANKING = "Files/Rank/ranking.txt";
	private final String SAVE = "Files/Save/";
	
	public void save(String tag, ArrayList<IJugador> guardado) throws IOException {
		
		File archivo = new File(this.SAVE + tag + ".txt");
		PrintWriter escritor;
		String linea;
		
		archivo.delete();
		archivo.createNewFile();
		escritor = new PrintWriter(archivo);
		
		for (IJugador player : guardado) {
			
			linea = player.getNombre() + "," + (player.getDinero() - 1000);
			escritor.println(linea);
			
		}
		
		escritor.close();
		
	}
	
}
