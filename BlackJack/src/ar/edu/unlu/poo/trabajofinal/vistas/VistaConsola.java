package ar.edu.unlu.poo.trabajofinal.vistas;

import ar.edu.unlu.poo.misfunciones.Escaner;
import ar.edu.unlu.poo.misfunciones.Print;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Menu;
import ar.edu.unlu.poo.trabajofinal.commons.OpcionesMenuPrincipal;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class VistaConsola extends Vista {
	
	private Print p;
	private BlackJack controlador;
	private Menu menu;
	private OpcionesMenuPrincipal menuPrincipal = OpcionesMenuPrincipal.JUGAR;
	private Escaner sc;
	
	public VistaConsola(BlackJack controlador) {
		
		super();
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
			
			this.menuPrincipal();
			
		}
		else if (eleccion == OpcionesMenuPrincipal.SALIR.getId()) {
			
			p.print("Nos re vimos!");
			System.exit(0);
			
		}
		else if (eleccion == OpcionesMenuPrincipal.RANKING.getId()) {
			
			
			try {
				this.ranking();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		else if (eleccion == OpcionesMenuPrincipal.CARGAR.getId()) {
	
			try {
				this.carga();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		else if (eleccion == OpcionesMenuPrincipal.HELP.getId()) {
			
			try {
				this.help();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	public void mostrarMensaje(IMensaje msj) {
		
		String remitente = msj.getRemitente();
		
		if (msj.getDescripcion() != "") {
			
			p.espacio();	
			p.print(remitente + ": ");
			p.justPrint(msj.getDescripcion());
			
			/*
				p.print();
				p.print("Press ENTER to continue: ");
				sc.enter();
			 */
			
		}

	}

	public void formularioSetApuesta(String nombre) {
		
		String monto;
		
		p.espacio();
		p.print();
		p.print(nombre + " " + "ingrese su apuesta: ");

		p.print("(Recuerde que la puesta mínima es de " + controlador.getApuestaMinima() + ")");
		monto = sc.next();
		
		this.controlador.apostar(monto);
		
	}
	
	@Override
	public boolean siONo(IMensaje msj) {
		
		boolean res;
		
		p.espacio();
		p.print();
		p.print(msj.getRemitente());
		p.print(msj.getDescripcion());
		res = sc.siONo();
		return res;
		
	}
	
	@Override
	public void guardado() throws IOException {
			
		p.espacio();
		p.print("Ingrese el nombre del archivo de guardado: ");
		this.controlador.guardar(sc.next());
		this.menuPrincipal();
		
	}

	@Override
	public void carga() throws IOException {
		
		
		File dir = new File("Files/Save");
		File[] archivos = dir.listFiles();
		String[] strs = new String[archivos.length];
		int contador = 0;
		int res = 0;
		
		p.espacio();
		p.printConEspacio("Carga de partida");
		p.printConEspacio("Seleccione el número de la partida que quiere recuperar");
		
		for (File archivo : archivos) {
			
			strs[contador] = archivo.getName();
			p.print(contador + "- " + strs[contador]);
			contador++;
			
		}
		
		res = sc.nextInt();
		
		if ((res > archivos.length) || (res < 0)){
			
			p.printConEspacioAlto("El número no corresponde a una opción existente!");
			this.menuPrincipal();
			
		}
		else {
			
			this.controlador.cargar(strs[res]);
			
		}
		
	}
	
	@Override
	public void ranking() throws IOException {
		
		ArrayList<String> listaStr = this.controlador.getRanking();
		String[] spliteo;
		String nombre;
		String puntos;
		int contador = 1;
		
		p.espacio();
		p.printConEspacio("RANKING");
		
		for (int e = (listaStr.size() - 1); e >= 0; e--) {
			
			spliteo = listaStr.get(e).split(",");
			nombre = spliteo[0].trim();
			puntos = spliteo[1].trim();
			p.print(String.valueOf(contador) + ". " + nombre + " - Puntos: " + puntos);
			contador++;

		}
		
		this.menuPrincipal();
		
	}
	
	@Override
	public void help() throws IOException {
		
		ArrayList<String> texto = this.controlador.getHelp();
		
		
		p.espacio();
		
		for (String line : texto) {
			
			if (line.startsWith("//")) {
				
				p.printConEspacio(line.replaceAll("//", ""));
				
			}
			else if (line.startsWith(">")) {
				
				p.print();
				
			}
			else {
				
				p.print(line);
				
			}
				
		}
		
		this.menuPrincipal();
		
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
			
			try {
				conjuntoNombres[contador] = dato.getNombre();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				conjuntoNombres[contador] = "Error nombre";
			}
			
			try {
				conjuntoCartas.add(dato.getCartas());
			} catch (RemoteException e1) {
				conjuntoCartas.add(null);
				e1.printStackTrace();
			}
			
			try {
				conjuntoPuntajes[contador] = "Puntaje: " + String.valueOf(dato.getPuntaje());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				conjuntoPuntajes[contador] = "Remote exception";
			}
			
			// No le agrega el dinero al crupier.
			if (contador != (size - 1)) {
				
				try {
					dinerillo[contador] = "Dinero: " + String.valueOf(dato.getDinero());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					dinerillo[contador] = "Error Dinero";
				}
				
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
