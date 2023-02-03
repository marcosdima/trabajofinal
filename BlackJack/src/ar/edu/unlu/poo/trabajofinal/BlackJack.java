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
	public static final int MAXIMODEJUGADORES = 4;
	private static int JUGADORES;

	public BlackJack() {
		
		crupier = new CrupierBlackJack(MAXIMODEJUGADORES);
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
			this.crupier.addJugador(nombre);
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
			monto = "0";
			this.actualizar(Evento.MOSTRARMANO, this.getDatosJugadores());
		
		}
		
		try {
			this.crupier.setApuestas(monto);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<IJugador> getDatosJugadores() {

		ArrayList<IJugador> result = null;
		
		try {
			result = this.crupier.getDatosJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
		
	}
	
	public void setApuestaMinima(int monto) {
		
		this.crupier.setApuestaMinima(monto);
		
	}
	
	public int getApuestaMinima() {
		
		return this.crupier.getApuestaMinima();
		
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
	
	public int getDineroBase() {
		
		return this.crupier.getDineroBase();
				
	}

	public void setDineroBase(int dineroBase) {
		
		this.crupier.setDineroBase(dineroBase);
		
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
						
						this.crupier.terminarTurnoJugador(jugadorBJ);
						
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
						
						vista.formularioSetApuesta(data);;
						
					}

			default:
				
				;
				}
				
			}
			
		}
		
	};

	@Override
	public void actualizar(Evento event) {
		
		boolean salir;
		
		for (IVista vista : this.interfaces) {
			
			if (vista.isActiva()) {
				
				switch ((Evento) event) {
				
				case PRIMERAPUESTA:

					try {
						vista.formularioSetApuesta(this.crupier.getApostador());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				case FINDEMANO:
				
					this.crupier.definirGanadores();
					salir = this.crupier.reiniciarMano();
					
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
