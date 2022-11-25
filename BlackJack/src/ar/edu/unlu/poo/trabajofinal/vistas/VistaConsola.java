package ar.edu.unlu.poo.trabajofinal.vistas;

import ar.edu.unlu.poo.misfunciones.Escaner;
import ar.edu.unlu.poo.misfunciones.Print;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Menu;
import ar.edu.unlu.poo.trabajofinal.commons.OpcionesMenuPrincipal;
import java.util.ArrayList;

public class VistaConsola implements IVista {
	
	private Print p;
	private BlackJack controlador;
	private Menu menu;
	private OpcionesMenuPrincipal menuPrincipal = OpcionesMenuPrincipal.JUGAR;
	private Escaner sc;
	
	public VistaConsola(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		p = new Print();
		menu = new Menu(menuPrincipal);
		sc = new Escaner();
		
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
			System.exit(0);
			
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

	public void mostrarMano(ArrayList<IJugador>  datos) {
			
		p.espacio();
		this.printJugadores(datos);
			
	}
	
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj, IJugador data) {
		
		if (msj.getDescripcion() != "") {
			
			p.espacio();	
			p.print(data.getNombre() + ": ");
			p.justPrint(msj.getDescripcion());
			
			/*
				p.print();
				p.print("Press ENTER to continue: ");
				sc.enter();
			 */
			
		}

	}

	public void formularioSetApuesta(IJugador dato) {
		
		String monto;
		
		p.espacio();
		p.print();
		p.print(dato.getNombre() + " " + "ingrese su apuesta: ");
		p.print("(Recuerde que la puesta mínima es de " + controlador.getApuestaMinima() + ")");
		monto = sc.next();
		
		this.controlador.apostar(monto);
		
	}
	
	@Override
	public boolean siONo(IMensaje msj, IJugador data) {
		
		boolean res;
		
		p.espacio();
		p.print();
		p.print(data.getNombre());
		p.print(msj.getDescripcion());
		res = sc.siONo();
		return res;
		
	}
	
	//////////////////////////////////
	//		Métodos de Consola		//
	//////////////////////////////////
	
	private void printJugadores(ArrayList<IJugador> datos) {
		
		int contador = 0;
		int espacio = 25;
		int size = datos.size();
		String[] conjuntoNombres = new String[size];
		ArrayList<String[]> conjuntoCartas = new ArrayList<String[]>(size);
		String[] conjuntoPuntajes = new String[size];
		String[] dinerillo = new String[size];

		for (IJugador dato : datos) {
			
			conjuntoNombres[contador] = dato.getNombre();
			conjuntoCartas.add(dato.getCartas());
			conjuntoPuntajes[contador] = "Puntaje: " + String.valueOf(dato.getPuntaje());
			
			// No le agrega el dinero al crupier.
			if (contador != (size - 1)) {
				
				dinerillo[contador] = "Dinero: " + String.valueOf(dato.getDinero());
				
			}
			else {
				
				dinerillo[contador] = "";
				
			}
			
			contador++;
			
		}
		
		p.printSeguido(conjuntoNombres, espacio);
		p.printSeguido(conjuntoCartas, espacio);
		p.printSeguido(conjuntoPuntajes, espacio);
		p.printSeguido(dinerillo, espacio);
		
	}

}
