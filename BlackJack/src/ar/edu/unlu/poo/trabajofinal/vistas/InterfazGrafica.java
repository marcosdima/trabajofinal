package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ar.edu.unlu.poo.gui.Boton;
import ar.edu.unlu.poo.gui.Frame;
import ar.edu.unlu.poo.gui.ImageManager;
import ar.edu.unlu.poo.gui.Label;
import ar.edu.unlu.poo.gui.Panel;
import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;

public class InterfazGrafica extends Vista {

	private BlackJack controlador;
	private Frame frame;
	private ImageManager imageManager;
	private ArrayList<String> nombres = new ArrayList<String>();
	private boolean flag = false;
	
	public InterfazGrafica(BlackJack controlador) {
		
		super();
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		this.setFrame();
		this.setImageManager("Imagenes/Cartas/", "Default");
		
	}
	
	@Override
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void menuPrincipal() {
		
		JButton salir = new JButton("Salir");
		JButton jugar = new JButton("Jugar");
		JButton load = new JButton("Cargar");
		JButton rank = new JButton("Ranking");
		
		Component[] opciones = {jugar, salir, load, rank};
		
		PanelMenuPrincipal framecito = new PanelMenuPrincipal(opciones, 10, 10);
		
		jugar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				nombres = new ArrayList<String>();
				flag = false;
				setAgregarJugadores();
				formularioAgregarJugador();
				
			}

		});;
		salir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}});
		load.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					carga();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		rank.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ranking();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		
		this.frame.add(framecito);
		framecito.updateUI();
		
	}

	@Override
	public void formularioAgregarJugador() {
	
		String nombre = "";

		if (this.nombres.size() > 0) {
			
			nombre = this.nombres.get(0);
			
			if (this.nombres.size() > 1) {
				
				this.nombres.remove(0);
				
				
			}

			this.controlador.addJugador(nombre);
			
		}

	}

	@Override
	public void menuConfiguracion() {
		
		
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj, IJugador data) {
		
		JOptionPane mensaje = null;
		
		if (!flag) {
			
			if (msj instanceof Evento) {
					
				if ((msj == Evento.FINDELJUEGO)) {
					
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "El juego terminó!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					this.flag = true;
					
				}
				else {
					
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), data.getNombre(), JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
				}
	
			}
			if (msj instanceof SaltoError) {
				
				JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "Error detectado!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				
			}
			if (msj instanceof Notificacion) {
				
				System.out.println(msj);
				
			}
			
		}

	}

	@Override
	public void mostrarMano(ArrayList<IJugador> datos) {
		
		int i = 0;
		int largo = datos.size() - 1;	
		Panel panel = new Panel();
		Panel jugadores = new Panel();
		Panel crupier = new Panel();
		GridLayout grid = new GridLayout(2,1);
		GridLayout gridPlayers = new GridLayout(1,5);
		ModuloJugador auxiliar = null;
		
		panel.setLayout(grid);
		jugadores.setLayout(gridPlayers);
		crupier.setLayout(gridPlayers);

		for (i = 0; i < BlackJack.MAXIMODEJUGADORES; i++) {
			
			if (i < largo) {
				
				auxiliar = new ModuloJugador(datos.get(i), this.getImageManager());
				jugadores.add(auxiliar);
				
			}
			else {
				
				jugadores.addVacio();
				
			}
			
		}
		
		// Crupier.
		auxiliar = new ModuloJugador(datos.get(largo), this.getImageManager());
		
		crupier.addVacio();
		crupier.addVacio();
		crupier.add(auxiliar);
		crupier.addVacio();
		crupier.addVacio();
		
		panel.add(jugadores);
		panel.add(crupier);
		
		this.frame.append(panel);

	}

	@Override
	public void formularioSetApuesta(IJugador dato) {
		
		if (!flag) {
			
			JOptionPane pane = new JOptionPane("Hola", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION);
			String res = JOptionPane.showInputDialog(pane, "Ingrese su apuesta:", dato.getNombre(), JOptionPane.INFORMATION_MESSAGE);	
			this.controlador.apostar(res);
			
		}
		
	}

	@Override
	public boolean siONo(IMensaje msj, IJugador dato) {
		
		boolean retorno = false;
		
		if (!dato.getNombre().equals("Crupier")) {
			
			
			JOptionPane mensaje = new JOptionPane(dato.getNombre() + ": " + msj.getDescripcion(), JOptionPane.YES_NO_OPTION);
			// 0 = Si; 1 = No
			int respuesta = JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), dato.getNombre(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (respuesta == 0) {
				
				retorno = true;
				
			}
			
		}
		
		return retorno;
		
	}

	public void guardado() throws IOException {
		
		JOptionPane mensaje = new JOptionPane();
		int respuesta = JOptionPane.showConfirmDialog(mensaje, "Quieres guardar tu partida?",
				"Guardado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (respuesta == 0) {
			
			this.controlador.guardar("as");
			
		}
		
		System.exit(0);

	}
	
	@Override
	public void carga() throws IOException {
		
		Frame cargados = new Frame("Carga");
		BorderLayout border = new BorderLayout(10,100);
		Panel principal = new Panel();
		Boton seguir = new Boton("Cargar");
		JComboBox<String> lista;
		
		// Estos son para crear la lista de archivos disponibles.
		File dir = new File("Files/Save");
		File[] archivos = dir.listFiles();
		String[] strs = new String[archivos.length + 1];
		int contador = 1;
		
		strs[0] = "Seleccionar...";
		
		for (File archivo : archivos) {
			
			strs[contador] = archivo.getName();
			contador++;
			
		}

		lista = new JComboBox<String>(strs) ;
		// Hasta aca.
		
		seguir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String eleccion = (String) lista.getSelectedItem();
				
				cargados.setVisible(false);
				frame.setEnabled(true);
				
				if (eleccion != strs[0]) {
					
					try {
						// Este flag es por un error en los mensajes.
						flag = false;
						controlador.cargar(eleccion);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				else {
					
					JOptionPane.showConfirmDialog(principal, "Ese archivo no existe!", "Advertencia!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
				}

			}});
	
		frame.setEnabled(false);
		
		cargados.setSize(300, 600);
		cargados.setVisible(true);
		
		principal.setLayout(border);

		principal.add(seguir, BorderLayout.SOUTH);
		principal.addVacio(BorderLayout.NORTH);
		principal.add(lista, BorderLayout.CENTER);
		principal.addVacio(BorderLayout.WEST);
		principal.addVacio(BorderLayout.EAST);
		
		cargados.append(principal);
		principal.updateUI();
		
	}
	
	@Override
	public void ranking() throws IOException {
		
		Frame cargados = new Frame("Ranking");
		GridLayout grid = new GridLayout(10,1);
		ArrayList<String> listaStr = controlador.getRanking();
		Panel rank = new Panel();
		JLabel linea = new JLabel();
		Font fuente = new Font("FreeMono", Font.ITALIC, linea.getFont().getSize() + 2);
		String[] spliteo = null;
		String nombre = "";
		String puntos = "";
		int contador = 1;
		
		cargados.setSize(300, 600);
		cargados.setVisible(true);
		rank.setLayout(grid);
		linea.setFont(fuente);
		
		if (!listaStr.isEmpty()) {
			
			for (String str : listaStr) {
				
				spliteo = str.split(",");
				nombre = spliteo[0].trim();
				puntos = spliteo[1].trim();
				linea.setText((String.valueOf(contador) + ". " + nombre + " - Puntos: " + puntos));;
				rank.add(linea);
				contador++;

			}
			
			//rank = new PanelMenuPrincipal(componentes);
			cargados.append(rank);
			rank.updateUI();
			
		}
		else {
			
			JOptionPane.showMessageDialog(cargados, "Actualmente no se encuentrar cargados jugadores en el ranking, "
					+ "empieza a jugar para ir sumando records!", "No hay ranking!", JOptionPane.INFORMATION_MESSAGE);
			cargados.setVisible(false);
			
		}
		
	}
	
	// Metodos de intefazgráfica.
	
	private void setAgregarJugadores() {

		int maximo = 5;
		ModuloAgregarJugador jugador;
		PanelGrilla agregar;
		ModuloAgregarJugador[] modulos = new ModuloAgregarJugador[maximo];
		JButton seguir = new JButton("Seguir");
		
		for (int i = 0; i < maximo; i++) {
			
			jugador = new ModuloAgregarJugador();	
			modulos[i] = jugador;

		}
		
		for (ModuloAgregarJugador player : modulos) {
			
			player.getAdd().addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					
					// Acá habría que hacer la verificación de las entradas.
					String nombre = player.getText();
					nombres.add(nombre);
					player.apagar();
					
				}
				
			});

		}
		
		agregar = new PanelGrilla(modulos, 2, 3, 50, 50);
		
		seguir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (nombres.size() > 0) {
					
					nombres.add(null);
					formularioAgregarJugador();
					
				}
		
			}
			
		});
		agregar.add(seguir);

		this.frame.append(agregar);
		
	}

	public ImageManager getImageManager() {
		return this.imageManager;
	}

	public void setImageManager(String source, String folder) {
		this.imageManager = new ImageManager(source, folder);
	}

	public void setFrame() {
		
		this.frame = new Frame("Black Jack");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}




}

