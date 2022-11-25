package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
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

public class InterfazGrafica implements IVista {

	private BlackJack controlador;
	private Frame frame;
	private ImageManager imageManager;
	private ArrayList<String> nombres = new ArrayList<String>();
	private boolean flag = false;
	
	public InterfazGrafica(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		this.frame = new Frame("Black Jack");
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
		
		Component[] opciones = {jugar, salir};
		
		PanelMenuPrincipal framecito = new PanelMenuPrincipal(opciones, 10, 10);
		
		jugar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				nombres = new ArrayList<String>();
				formularioAgregarJugador();
				
			}

		});;
		salir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}});
		
		this.frame.add(framecito);
		framecito.updateUI();
		
	}

	@Override
	public void formularioAgregarJugador() {
	
		String nombre = "";
		
		// Le saqué el flag.
		if (this.nombres.size() > 0) {
			
			nombre = this.nombres.get(0);
			
			if (this.nombres.size() > 1) {
				
				this.nombres.remove(0);
				
			}
			
			this.controlador.addJugador(nombre);
			
		}
		else {
					
			this.frame.append(this.setAgregarJugadores());
			
		}
		
	}

	@Override
	public void menuConfiguracion() {
		
		
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj, IJugador data) {
		
		JOptionPane mensaje;
		
		if (msj instanceof Evento) {
				
			if ((msj == Evento.FINDELJUEGO) && (!flag)) {
				
				mensaje = new JOptionPane(data.getNombre() + ": " + msj.getDescripcion());
				JOptionPane.showConfirmDialog(mensaje, msj.getDescripcion(), "El juego terminó!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				this.flag = true;
				
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
		
		JOptionPane pane = new JOptionPane("Hola",JOptionPane.INFORMATION_MESSAGE, JOptionPane.NO_OPTION);
		String res = JOptionPane.showInputDialog(pane, "Ingrese su apuesta:");	
		this.controlador.apostar(res);
	
		
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

	// Metodos de intefazgráfica.
	
	private PanelGrilla setAgregarJugadores() {

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
		
		agregar = new PanelGrilla(modulos, 2, 3);
		
		seguir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				nombres.add(null);
				formularioAgregarJugador();
				
			}
			
		});
		agregar.add(seguir);

		return agregar;
		
	}

	public ImageManager getImageManager() {
		return this.imageManager;
	}

	public void setImageManager(String source, String folder) {
		this.imageManager = new ImageManager(source, folder);
	}

}

