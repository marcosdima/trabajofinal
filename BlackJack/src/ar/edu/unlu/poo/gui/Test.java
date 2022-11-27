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
		
		Label l = new Label("Que onda?");
		Boton v = new Boton("sup");
		
		Frame f = new Frame("test");
		
		f.setVisible(true);
		l.setSizeFont(100);
		f.getContentPane().add(l);

		
	}

}
