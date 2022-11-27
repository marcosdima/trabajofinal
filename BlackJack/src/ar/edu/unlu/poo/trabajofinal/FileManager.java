package ar.edu.unlu.poo.trabajofinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	
	private final String RANKING = "Files/Rank/ranking.txt";
	private final String SAVE = "Files/Save/";
	
	public void save(String tag, ArrayList<String> guardado) throws IOException {
		
		File archivo = new File(this.SAVE + tag + ".txt");
		PrintWriter escritor;

		archivo.createNewFile();
		escritor = new PrintWriter(archivo);
		
		for (String player : guardado) {
			
			escritor.println(player);
			
		}
		
		escritor.close();
		
	}
	
	public ArrayList<String> load(String tag) throws IOException {
		
		File archivo = new File(this.SAVE + tag);
		FileReader fr = new FileReader(archivo);;	
		BufferedReader reader = new BufferedReader(fr);
		ArrayList<String> retorno = new ArrayList<String>();
		String linea = reader.readLine();
		
		while ((linea != null)) {
			
			retorno.add(linea);
			linea = reader.readLine();
			
		}

		reader.close();
		
		return retorno;
		
	}
	
}
