package ar.edu.unlu.poo.trabajofinal.vistas;

import ar.edu.unlu.poo.misfunciones.Print;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.DatosDeJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Menu;
import ar.edu.unlu.poo.trabajofinal.commons.OpcionesMenuPrincipal;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola implements IVista {
	
	private Print p;
	private BlackJack controlador;
	private Menu menu;
	private OpcionesMenuPrincipal menuPrincipal = OpcionesMenuPrincipal.JUGAR;
	private Scanner sc;
	
	public VistaConsola(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		p = new Print();
		menu = new Menu(menuPrincipal);
		sc = new Scanner(System.in);
		
	}
	
	public void menuPrincipal() {
		
		int eleccion;
		
		p.espacio();
		menu.setOpciones(menuPrincipal);
		menu.print();
		
		eleccion = sc.nextInt();
		
		if (eleccion == OpcionesMenuPrincipal.JUGAR.getId()) {
			
			this.formularioAgregarJugador();
			
		}
		else if (eleccion == OpcionesMenuPrincipal.CONFIGURACION.getId()) {
			
			this.menuConfiguracion();
			
		}
		else if (eleccion == OpcionesMenuPrincipal.SALIR.getId()) {
			
			p.print("Nos re vimos!");
			
		}
		else {
			
			p.print("Esa opción no se corresponde con ninguna de las opciones");
			this.menuPrincipal();
			
		}
		
	}
	
	public void formularioAgregarJugador() {
		
		String nombre;
		
		p.espacio();
		p.print("Ingrese el nombre del jugador: ");
		p.print("('p' para empezar a jugar)");
		nombre = sc.next();
		
		if (nombre.equals("p")) {
			
			this.controlador.addJugador(null);
			
		}
		else {
			
			this.controlador.addJugador(nombre);
		
		}
	}

	public void menuConfiguracion() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarMano(ArrayList<DatosDeJugador>  datos) {
			
		p.espacio();
		this.printJugadores(datos);
			
	}
	
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj) {
		
		if (msj.getDescripcion() != "") {
			
			p.espacio();	
			p.printConEspacio(msj.getDescripcion());
			
		}

	}

	public void formularioSetApuesta(DatosDeJugador dato) {
		
		int monto;
		
		p.espacio();
		p.print(dato.getNombre() + " " + "ingrese su apuesta: ");
		p.print("(Recuerde que la puesta mínima es de " + controlador.getApuestaMinima() + ")");
		monto = sc.nextInt();
		
		this.controlador.apostar(monto);
		
	}
	
	@Override
	public boolean siONo(IMensaje msj) {
		
		// DESPUES HAY QUE CREAR LA CLASE SCANNER PROPIA!
		boolean res = false;
		String respuesta;
		
		p.print();
		respuesta = sc.next();
		
		if (respuesta.toLowerCase() == "si") {
			
			res = true;
			
		}
		
		return res;
		
	}
	
	//////////////////////////////////
	//		Métodos de Consola		//
	//////////////////////////////////
	
	private void printJugadores(ArrayList<DatosDeJugador> datos) {
		
		int contador = 0;
		int espacio = 25;
		int size = datos.size();
		String[] conjuntoNombres = new String[size];
		ArrayList<String[]> conjuntoCartas = new ArrayList<String[]>(size);
		String[] conjuntoPuntajes = new String[size];

		
		
		for (DatosDeJugador dato : datos) {
			
			if (dato.sigueJugando()) {

				conjuntoNombres[contador] = dato.getNombre();
				conjuntoCartas.add(dato.getCartas());
				conjuntoPuntajes[contador] = "Puntaje: " + dato.getPuntaje();
				contador++;

				
			}
			
		}
		
		p.printSeguido(conjuntoNombres, espacio);
		p.printSeguido(conjuntoCartas, espacio);
		p.printSeguido(conjuntoPuntajes, espacio);
		
	}

	


}
