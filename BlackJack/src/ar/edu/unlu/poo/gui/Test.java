package ar.edu.unlu.poo.gui;

public class Test {

	public static void main(String[] args) {
		
		String res = null;
		Integer.valueOf(res);
		
		
		ImageManager m = new ImageManager("Imagenes/Cartas/", "Default");
		
		
		Frame fr = new Frame("test");
		
		fr.append(m.imagen("AS_TREBOL"));
		
		MessageDialogCustom mes = new MessageDialogCustom("Cartel", "Nada", MessageDialogCustom.SI_O_NO);
		mes.showMessage();
		
		
		

	}

}
