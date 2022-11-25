package ar.edu.unlu.poo.trabajofinal.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.unlu.poo.trabajofinal.BlackJack;
import ar.edu.unlu.poo.trabajofinal.IJugador;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;

public class InterfazGrafica implements IVista {

	private BlackJack controlador;
	private JFrame frame;
	private JFrame frameMesa;
	
	private ArrayList<String> nombres = new ArrayList<String>();;
	
	public InterfazGrafica(BlackJack controlador) {
		
		this.setControlador(controlador);
		this.controlador.addIntefaz(this);
		this.setFrame();
		
	}
	
	@Override
	public void setControlador(BlackJack controlador) {
		
		this.controlador = controlador;
		
	}

	@Override
	public void menuPrincipal() {

		this.getPanelPrincipal().add(this.setMenuPrincipal());
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
					
			this.getPanelPrincipal().add(this.setAgregarJugadores());
			
		}
		
	}

	@Override
	public void menuConfiguracion() {
		
		
		
	}

	@Override
	public void mostrarMensaje(IMensaje msj, IJugador data) {
		
		System.out.println(msj);

	}

	@Override
	public void mostrarMano(ArrayList<IJugador> datos) {
		
		int i = 0;
		int largo = datos.size() - 1;	
		JPanel panel = new JPanel();
		JPanel jugadores = new JPanel();
		ModuloJugador m = null;
		GridLayout grid = new GridLayout(2,1);
		FlowLayout flow = new FlowLayout();
		
		panel.setLayout(grid);
		jugadores.setLayout(flow);

		for (i = 0; i < largo; i++) {
			
			 m = new ModuloJugador(datos.get(i));
			 jugadores.add(m);
			
		}
		
		// Crupier.
		m = new ModuloJugador(datos.get(largo));
		
		panel.add(jugadores);
		panel.add(m);
		
		this.frameMesa.setContentPane(panel);
		panel.updateUI();

	}

	@Override
	public void formularioSetApuesta(IJugador dato) {
		
		String nro = JOptionPane.showInputDialog(dato.getNombre() + "Ingrese su apuesta: ");
		this.controlador.apostar(Integer.valueOf(nro));
		
	}

	@Override
	public boolean siONo(IMensaje msj, IJugador dato) {
		
		boolean retorno = false;
		JOptionPane mensaje = new JOptionPane(dato.getNombre() + ": " + msj.getDescripcion(), JOptionPane.YES_NO_OPTION);
		// 0 = Si; 1 = No
		int respuesta = JOptionPane.showConfirmDialog(mensaje, "Quieres otra carta?", "Turno de" + dato.getNombre(), JOptionPane.YES_NO_OPTION, 0);

		if (respuesta == 0) {
			
			retorno = true;
			
		}
		
		System.out.println(retorno);
		
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
				frameMesa = new JFrame("Black Jack");	
				frameMesa.setSize(1200, 800);
				frameMesa.setResizable(false);
				
				frameMesa.setVisible(true);
				frame.setVisible(false);
				formularioAgregarJugador();
				
			}
			
		});
		agregar.add(seguir);

		return agregar;
		
	}
	
	private PanelMenuPrincipal setMenuPrincipal() {
		
		JButton salir = new JButton("Salir");
		JButton jugar = new JButton("Jugar");
		
		Component[] opciones = {jugar, salir};
		
		PanelMenuPrincipal framecito = new PanelMenuPrincipal(opciones, 10, 10);
		
		jugar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				framecito.setVisible(false);;
				formularioAgregarJugador();
				
			}

		});;
		salir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}});
		
		return framecito;
		
	}

	private void setMesa(JPanel datos) {
		
		this.frameMesa.removeAll();;
		//this.frameMesa = new JFrame();
		//this.frameMesa.setSize(1200, 800);
		this.frameMesa.setVisible(true);
		this.frameMesa.getContentPane().add(datos);
		
	}
	
	private void setFrame() {
	
		JFrame frame = new JFrame("Black Jack");		
		frame.setSize(1200, 800);
		frame.setVisible(true);
		this.frame = frame;
	
	}
	
	private JPanel getPanelPrincipal() {
		
		return (JPanel) this.frame.getContentPane();
		
	}
	
}

