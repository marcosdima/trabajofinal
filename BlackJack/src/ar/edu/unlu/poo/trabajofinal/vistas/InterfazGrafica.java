package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ar.edu.unlu.poo.gui.Frame;
import ar.edu.unlu.poo.gui.ImageManager;
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
		JButton save = new JButton("Guardar");
		
		Component[] opciones = {jugar, salir, save};
		
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
		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					salidaForzada();
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
		
		JOptionPane mensaje;
		
		if (!flag) {
			
			if (msj instanceof Evento) {
					
				if ((msj == Evento.FINDELJUEGO)) {
					
					mensaje = new JOptionPane(data.getNombre() + ": " + msj.getDescripcion());
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "El juego terminó!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					this.flag = true;
					
				}
				else {
					
					mensaje = new JOptionPane(data.getNombre() + ": " + msj.getDescripcion());
					JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "Evento", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
				}
	
			}
			if (msj instanceof SaltoError) {
				
				mensaje = new JOptionPane(data.getNombre() + ": " + msj.getDescripcion());
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
		JOptionPane mensaje = new JOptionPane(dato.getNombre() + ": " + msj.getDescripcion(), JOptionPane.YES_NO_OPTION);
		// 0 = Si; 1 = No
		int respuesta = JOptionPane.showConfirmDialog(mensaje, "Quieres otra carta?", "Turno de" + dato.getNombre(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (respuesta == 0) {
			
			retorno = true;
			
		}
		
		return retorno;
		
	}

	public void salidaForzada() throws IOException {
		
		JOptionPane mensaje = new JOptionPane("Hola", JOptionPane.YES_NO_OPTION);
		// 0 = Si; 1 = No
		int respuesta = JOptionPane.showConfirmDialog(mensaje, "Estas seguro de que quieres salir?", 
							"Salir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (respuesta == 0) {
			
			respuesta = JOptionPane.showConfirmDialog(mensaje, "Quieres guardar tu partida?",
					"Guardado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (respuesta == 0) {
				
				this.controlador.guardar("as");
				
			}
			
			System.exit(0);
			
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
		
	}
	
}

