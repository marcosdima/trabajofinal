package ar.edu.unlu.poo.mistads;

public abstract class Dato {

	private String clave;
	private int infoInt;
	
	public Dato(String data, int dataDos) {
		
		this.setInfo(data);
		this.setInfoInt(dataDos);
		
	}
	
	public Dato(String data) {
		
		this (data, 0);
		
	}
	
	public Dato(int data) {
		
		this ("", data);
		
	}

	public Dato() {
		
		this ("", 0);
		
	}
	
 	public String getClave() {
		return clave;
	}

	public void setInfo(String info) {
		this.clave = info;
	}

	public int getInfoInt() {
		return infoInt;
	}

	public void setInfoInt(int infoInt) {
		this.infoInt = infoInt;
	}
	
}
