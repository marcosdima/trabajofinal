package ar.edu.unlu.poo.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.poo.trabajofinal.commons.Evento;
import ar.edu.unlu.poo.trabajofinal.commons.IMensaje;
import ar.edu.unlu.poo.trabajofinal.commons.Notificacion;
import ar.edu.unlu.poo.trabajofinal.commons.SaltoError;
import ar.edu.unlu.poo.trabajofinal.vistas.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class BlackJack implements IControladorRemoto {
	
	private ICrupier crupier;
	private ArrayList<IVista> interfaces;
	private static int DINEROBASE = 1000;
	public static final int MAXIMODEJUGADORES = 4;

	public <T extends IObservableRemoto> BlackJack(T modelo) {
		try {
			this.setModeloRemoto(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BlackJack() {
		
		interfaces = new ArrayList<IVista>(2);
		
	}
	
	public void addIntefaz(IVista intefaz) {
		
		this.interfaces.add(intefaz);
		
	}
	
	public void addJugador(String nombre) {

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
	public void actualizar(IObservableRemoto arg0, Object event) throws RemoteException {
	
		IMensaje mensaje = (IMensaje) event;
		SaltoError error = null;
		Evento evento = null;
		
		JugadorBlackJack jugadorBJ;
		boolean guardar = false;
		boolean salir = false;
		
		if (event instanceof Notificacion) {
			
			for (IVista vista : this.interfaces) {
				
				vista.mostrarMensaje(mensaje);
				
			}
			
		}
		else if (event instanceof Evento) {
			
			evento = (Evento) event;
			
			for (IVista vista : this.interfaces) {
				
				if (vista.isActiva()) {
					
					vista.mostrarMano(this.getDatosJugadores());
					
					jugadorBJ = null;
					
					switch ((Evento) event) {
					
					case PREGUNTAROTRA:
						
						try {
							jugadorBJ = this.crupier.seleccionarJugador();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if (vista.siONo(evento)) {
							
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
						
						vista.mostrarMensaje(evento);
						break;
						
					case SINPLATA:
						
						vista.mostrarMensaje(evento);
						break;
						
					case ADVERTENCIAGUARDADO:
						
						salir = vista.siONo(evento);
						
						if (salir) {
								
							guardar = vista.siONo(Evento.GUARDAR);
							
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
						
					case PRIMERAPUESTA:

						try {
							vista.formularioSetApuesta(this.crupier.getApostador().getNombre());
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
							
							vista.mostrarMensaje(Evento.FINDEMANO);
							this.setInicio();
				
							vista.mostrarMano(this.getDatosJugadores());

							try {
								vista.formularioSetApuesta(this.crupier.getApostador().getNombre());
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							
							vista.mostrarMensaje(evento);
							vista.menuPrincipal();
							
						}	
						
					case FINDELJUEGO:
						
						vista.mostrarMensaje(evento);
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
		else if (event instanceof SaltoError) {
			
			error = (SaltoError) event;
			
			for (IVista vista : this.interfaces) {
				
				if (vista.isActiva()) {
					
					vista.mostrarMensaje(evento);
					
					switch (error) {
					
						case ERRORMAXJUGADORES:
							
							this.setInicio();
							
						case ERRORAPUESTA:
							
							vista.formularioSetApuesta(mensaje.getRemitente());
							
						case NOHAYJUGADORESCARGADOS:
							
							vista.formularioAgregarJugador();
							
						case ERRORFALTADEDINERO:
							
							vista.formularioSetApuesta(mensaje.getRemitente());
							
						case APOSTONONUMERO:
							
							vista.formularioSetApuesta(mensaje.getRemitente());
					
					default:
						break;
					
					}
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) throws RemoteException {
		
		this.crupier = (ICrupier) arg0;
		
	}

}
