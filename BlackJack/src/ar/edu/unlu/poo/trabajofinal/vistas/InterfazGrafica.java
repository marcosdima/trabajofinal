package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
		this.setBarra();
		
	}
	
	@Override
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void menuPrincipal() {
		
		JButton salir = new Boton("Salir");
		JButton jugar = new Boton("Jugar");
		JButton load = new Boton("Cargar");
		JButton rank = new Boton("Ranking");
		
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
	public void mostrarMensaje(IMensaje msj) {
		
		JOptionPane mensaje = null;
		String remitente = msj.getRemitente();
		
		if (!flag) {
			
			if (msj instanceof Evento) {
					
				if ((msj == Evento.FINDELJUEGO)) {
					
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "El juego terminó!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					this.flag = true;
					
				}
				else {
					
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), remitente, JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
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

		for (i = 0; i < (BlackJack.MAXIMODEJUGADORES + 1); i++) {
			
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
	public void formularioSetApuesta(String nombre) {
		
		if (!flag) {
			
			JOptionPane pane = new JOptionPane("Hola", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION);
			String res = JOptionPane.showInputDialog(pane, "Ingrese su apuesta: (Apuesta minima " + this.controlador.getApuestaMinima() + ")", nombre, JOptionPane.INFORMATION_MESSAGE);	
			
			if (res == null) {
				
				res = "a";
				
			}
			
			this.controlador.apostar(res);
			
		}
		
	}

	@Override
	public boolean siONo(IMensaje msj) {
		
		boolean retorno = false;
		
		if (!msj.getRemitente().equals("Crupier")) {
			
			JOptionPane mensaje = new JOptionPane(msj.getRemitente() + ": " + msj.getDescripcion(), JOptionPane.YES_NO_OPTION);
			// 0 = Si; 1 = No
			int respuesta = JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), msj.getRemitente(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (respuesta == 0) {
				
				retorno = true;
				
			}
			
		}
		
		return retorno;
		
	}

	public void guardado() throws IOException {
		

		JOptionPane pane = new JOptionPane("Hola", JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION);
		String nombreGuardado = JOptionPane.showInputDialog(pane, "Ingrese nombre de guardado:", "Guardar", JOptionPane.INFORMATION_MESSAGE);
		this.controlador.guardar(nombreGuardado);

	}
	
	@Override
	public void carga() throws IOException {
		
		Frame cargados = new Frame("Carga");
		BorderLayout border = new BorderLayout(10,50);
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
		
		cargados.setSize(300, 400);
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
		
		cargados.setSize(600, 600);
		cargados.setVisible(true);
		rank.setLayout(grid);
		linea.setFont(fuente);
		
		if (!listaStr.isEmpty()) {
			
			for (int e = (listaStr.size() - 1); e >= 0; e--) {
				
				spliteo = listaStr.get(e).split(",");
				nombre = spliteo[0].trim();
				puntos = spliteo[1].trim();
				linea = new Label(String.valueOf(contador) + ". " + nombre + " - Puntos: " + puntos);
				linea.setFont(fuente);
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
	
	// Para que no aparezca cada vez que arranca.
	public void setActiva(boolean activo) {

		super.setActiva(activo);
		this.frame.setVisible(activo);
		
	}
	
	public void help() throws IOException {
		
		JFrame help = new JFrame("Help");
		
		ArrayList<String> text = this.controlador.getHelp();
		Panel panel = new Panel();
		
		JTextArea texto = new JTextArea();		
		
		for (String line : text) {
			
			if (line.startsWith("//")) {
				
				texto.append(line.replaceAll("//", "") + '\n');
				texto.append("\n");
	
			}
			else if (line.startsWith(">")) {
				
				texto.append("\n");
				
			}
			else {
				
				texto.append(line + '\n');

			}

		}
		
		texto.setFont(new Font("FreeMono", Font.ITALIC, 20));
		texto.setEditable(false);

		panel.add(texto);
		panel.setBackground(new Color(255,255,255));
		help.getContentPane().add(panel);
		help.setSize(1200, 600);
		help.setVisible(true);
		
		texto.updateUI();
		
	}
	
	// Metodos de intefazgráfica.
	
	private void setAgregarJugadores() {

		int maximo = 5;
		ModuloAgregarJugador jugador;
		PanelGrilla agregar;
		ModuloAgregarJugador[] modulos = new ModuloAgregarJugador[maximo];
		Boton seguir = new Boton("Seguir");
		
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

	private void setBarra() {
		
		JMenuBar m = new JMenuBar();
		JMenu help = new JMenu("Help");
		JMenuItem comandos = new JMenuItem("Comandos");
		
		comandos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					help();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		});
		
		help.add(comandos);
		m.add(help);
		this.frame.setJMenuBar(m);
		
		
	}
	
}

