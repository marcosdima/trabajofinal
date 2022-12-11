package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.Observador;
import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;
import ar.edu.unlu.poo.trabajofinal.vistas.IVista;

public class BlackJack implements Observador {
	
	private CrupierBlackJack crupier;
	private ArrayList<IVista> interfaces;
	private static int DINEROBASE = 1000;
	public static final int MAXIMODEJUGADORES = 4;
	private static int JUGADORES;

	public BlackJack() {
		
		crupier = new CrupierBlackJack();
		this.crupier.agregarObservador(this);
		interfaces = new ArrayList<IVista>(2);
		BlackJack.JUGADORES = 0;
		
	}
	
	public void addIntefaz(IVista intefaz) {
		
		this.interfaces.add(intefaz);
		
	}
	
	public void addJugador(String nombre) {
		
		if (nombre == "") {
			
			nombre = "Player " + BlackJack.JUGADORES;
			
		}

		try {
			this.crupier.addJugador(nombre, BlackJack.DINEROBASE);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setInicio() {
		
		try {
			this.crupier.repartirPrimeraTanda();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void apostar(String monto) {
		
		if (monto.equals("cambiazo")) {
			
			this.cambiarVista();
			
		}
		
		try {
			this.crupier.setApuestas(monto);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<IJugador> getDatosJugadores() {

		ArrayList<IJugador> plys = null;
		
		try {
			plys = this.crupier.getDatosJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plys;
		
	}
	
	public int getApuestaMinima() {
		
		int apuestaMinima;
		
		try {
			apuestaMinima = this.crupier.getApuestaMinima();
		} catch (RemoteException e) {
			apuestaMinima = 0;
			e.printStackTrace();
		}
		
		return apuestaMinima;
		
	}
	
	public void iniciar() {
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				vista.menuPrincipal();
				
			}
			
		}
		
	}
	
	public void guardar(String tag) throws IOException {
		
		this.crupier.guardado(tag);
		
	}
	
	public void cargar(String tag) throws IOException {
		
		this.crupier.cargado(tag);
		
	}
	
	public ArrayList<String> getRanking() throws IOException {
		
		return this.crupier.getRanking();
		
	}
	
	public ArrayList<String> getHelp() throws IOException {
		
		return this.crupier.getHelp();
		
	}
	
	public void cambiarVista() {
		
		this.interfaces.get(0).setActiva(!this.interfaces.get(0).getActiva());;
		this.interfaces.get(1).setActiva(!this.interfaces.get(1).getActiva());;
		
	}
	
	//////////////////////////////////
	// Implementación de Observador //
	//////////////////////////////////
	
	@Override
	public void actualizar(Evento event, ArrayList<IJugador> objeto) {
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				switch ((Evento) event) {

				case MOSTRARMANO:
					
					vista.mostrarMano(objeto);
					
				default:;
			
				}
				
			}
		
		}
				
	}

	public void actualizar(Evento event, IJugador data) {
		
		JugadorBlackJack jugadorBJ;
		boolean guardar = false;
		boolean salir = false;
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				jugadorBJ = null;
				
				switch ((Evento) event) {
				
				case PREGUNTAROTRA:
					
					try {
						jugadorBJ = this.crupier.seleccionarJugador();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (vista.siONo(event, data)) {
						
						this.crupier.repartir(jugadorBJ);
						
					}
					else {
						
						try {
							this.crupier.terminarTurnoJugador(jugadorBJ);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}	
					
				case TERMINOTURNO:
					
					vista.mostrarMensaje(event, data);
					break;
					
				case SINPLATA:
					
					vista.mostrarMensaje(event, data);
					break;
					
				case ADVERTENCIAGUARDADO:
					
					salir = vista.siONo(event, data);
					
					if (salir) {
							
						guardar = vista.siONo(Evento.GUARDAR, data);
						
						if (guardar) {
							
							try {
								vista.guardado();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							
							vista.menuPrincipal();
							
						}
						
					}
					else {
						
						break;
						
					}

			default:
				
				;
				}
				
			}
			
		}
		
	};

	@Override
	public void actualizar(Evento event) {
		
		boolean salir = false;
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				switch ((Evento) event) {
				
				case PRIMERAPUESTA:

					try {
						vista.formularioSetApuesta(this.crupier.getApostador());
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				case FINDEMANO:
				
					try {
						salir = this.crupier.reiniciarMano();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (!salir) {
						
						vista.mostrarMensaje(Evento.FINDEMANO, this.crupier);
						this.setInicio();
			
						vista.mostrarMano(this.getDatosJugadores());

						try {
							vista.formularioSetApuesta(this.crupier.getApostador());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else {
						
						vista.mostrarMensaje(event, this.crupier);
						vista.menuPrincipal();
						
					}	
					
				case FINDELJUEGO:
					
					vista.mostrarMensaje(event, this.crupier);
					vista.menuPrincipal();
					break;
					
				case HELP:
					
					try {
						vista.help();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
				default:;
				
				}
				
			}

		}
		
	}

	@Override
	public void actualizar(SaltoError event, IJugador objeto) {
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				vista.mostrarMensaje(event, objeto);
				
				switch (event) {
				
					case ERRORMAXJUGADORES:
						
						this.setInicio();
						
					case ERRORAPUESTA:
						
						vista.formularioSetApuesta(objeto);
						
					case NOHAYJUGADORESCARGADOS:
						
						vista.formularioAgregarJugador();
						
					case ERRORFALTADEDINERO:
						
						vista.formularioSetApuesta(objeto);
						
					case APOSTONONUMERO:
						
						vista.formularioSetApuesta(objeto);
				
				default:
					break;
				
				}
				
			}
			
		}

	}
	
	@Override
	public void actualizar(Notificacion event, IJugador data) {
		
		IJugador playerContenedor;
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				playerContenedor = null;
				
				switch ((Notificacion) event) {
				
					case JUGADORCARGADO:
				
						vista.mostrarMensaje(event, data);
						vista.formularioAgregarJugador();		
						
					case BLACKJACK:
						
						vista.mostrarMensaje(event, data);
						break;
						
					case APUESTASETEADA:
					
					try {
						playerContenedor = this.crupier.getApostador();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						if (playerContenedor != null) {
							
							vista.mostrarMensaje(event, data);
							vista.formularioSetApuesta(playerContenedor);
							
						}
						else {
							
							try {
								this.crupier.repartir(this.crupier.seleccionarJugador());
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					case NOAPUESTA:
						
						try {
							playerContenedor = this.crupier.getApostador();
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (playerContenedor != null) {
							
							vista.mostrarMensaje(event, data);
							vista.formularioSetApuesta(playerContenedor);
							
						}
						else {
							
							this.actualizar(Evento.FINDEMANO);
							
						}
	
					default:
					
					;
				}
				
			}

		}

	}

}
