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
			
			this.menuConfiguracion();
			
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
			
			this.menuPrincipal();
			
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
		
		int res;
		
		p.espacio();
		p.printConEspacio("Menú de configuración");
		p.print("1- Modificar apuesta mínima. (" + this.controlador.getApuestaMinima() + ")");
		p.print("2- Modiificar monto inicial. (" + this.controlador.getDineroBase() + ") ");
		p.print("3- Salir");
		
		res = sc.nextInt();
		
		if (res == 1) {
			
			p.printConEspacioAlto("Ingrese la nueva apuesta mínima: ");
			res = sc.nextInt();
			
			
			if (res <= 0) {
				
				p.printConEspacioAlto("El monto inicial no puede ser menor o igula a 0!");
				
			}
			else {
				
				if (res > this.controlador.getDineroBase()) {
					
					this.controlador.setDineroBase(res);
					p.printConEspacioAlto("(Se modificó el monto mínimo para que no sea menor que la apuesta mínima)");
					
				}
				
				this.controlador.setApuestaMinima(res);
				
			}
			
			this.menuConfiguracion();
			
		}
		else if (res == 2) {
			
			p.printConEspacioAlto("Ingrese la nuevo monto inicial: ");
			res = sc.nextInt();
						
			if (res <= 0) {
				
				p.printConEspacioAlto("El monto inicial no puede ser menor o igula a 0!");
				
			}
			else {
				
				if (res < this.controlador.getApuestaMinima()) {
					
					this.controlador.setApuestaMinima(res);
					p.printConEspacioAlto("(Se modificó la apuesta mínima para que no sea mayor que el monto inicial)");
					
				}
				
				this.controlador.setDineroBase(res);

				
			}
			
			
			this.menuConfiguracion();
			
		}
		else if (res == 3) {
			
			this.menuPrincipal();
			
		}
		else {
			
			p.espacio();
			p.print("La opción ingresada no es válida!");
			this.menuConfiguracion();
			
		}
		
		
		
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
		
		if (dato != null) {
			
			String monto;
			
			p.espacio();
			p.print();
			p.print(dato.getNombre() + " " + "ingrese su apuesta: ");
			p.print("(Recuerde que la puesta mínima es de " + controlador.getApuestaMinima() + ")");
			monto = sc.next();
			
			this.controlador.apostar(monto);
			
		}	
		
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
