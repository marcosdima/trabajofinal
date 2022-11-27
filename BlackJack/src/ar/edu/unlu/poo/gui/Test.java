package ar.edu.unlu.poo.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.processing.SupportedSourceVersion;

import ar.edu.unlu.poo.trabajofinal.FileManager;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.JugadorBlackJack;

public class Test {

	public static void main(String[] args) throws IOException {
		
		
		
		FileManager f = new FileManager();
		ArrayList<String> j = new ArrayList<String>();
		
		File n = new File("Files/Save/testPrimero.txt");
		
		System.out.println(n.exists());
		
		j = f.load("testPrimero");
		
		for (String str : j) {
			
			System.out.println(str);
			
		}

	}

}
