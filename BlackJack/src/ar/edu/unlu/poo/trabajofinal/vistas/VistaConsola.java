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
		
		if (nombre == "p") {
			
			nombre = null;
			
		}
		
		this.controlador.addJugador(nombre);
			
	}

	public void menuConfiguracion() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void mostrarMano(ArrayList<DatosDeJugador>  datos) {
			
		// Preguntar si esta bien.
		int i = 0;
		ArrayList<DatosDeJugador> datosDeJugadores = (ArrayList<DatosDeJugador>) datos;
		DatosDeJugador dato = datosDeJugadores.get(i);
		DatosDeJugador datoCrupier = datosDeJugadores.get(datosDeJugadores.size() - 1);
		
		while ((!dato.todaviaNoJugo()) && (i < datosDeJugadores.size() - 1)) {
			
			i++;
			dato = datosDeJugadores.get(i);
			
		}
		
		this.printDatos(dato);
		this.printDatos(datoCrupier);
			
	}
	
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj) {
		
		if (msj.getDescripcion() != "") {
			
			p.espacio();	
			p.print(msj.getDescripcion());
			
		}

	}

	public void formularioSetApuesta() {
		
		int monto;
		
		p.print("Ingrese su apuesta: ");
		p.print("(Recuerde que la puesta mínima es de " + controlador.getApuestaMinima() + ")");
		monto = sc.nextInt();
		
		this.controlador.apostar(monto);
		
	}

	public void printDatos(DatosDeJugador datos) {
	
		p.printConEspacio(datos.getNombre());
		p.printConEspacio(datos.getCartas());
		p.printConEspacio(datos.getPuntaje());
		
	};
	
}
