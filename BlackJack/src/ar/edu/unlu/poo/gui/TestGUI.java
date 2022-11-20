package ar.edu.unlu.poo.gui;

public class TestGUI {

	public static void main(String[] args) {
		
		Frame coso = new Frame("Carlitos");
		
		coso.visible();
		
		Etiqueta t = new Etiqueta();

		for (int i = 0; i < 10; i++) {
			
			coso.add(t.getComponent());
			t = new Etiqueta();
		}

	}

}
