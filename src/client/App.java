package client;

import edu.utec.uy.bo.PersonaBO;
import edu.utec.uy.view.FuncionalidadVIEW2;

public class App {
	public static void main(String[] args) {
		System.out.println("Inicializando aplicación...");
		
		PersonaBO pBO = new PersonaBO();

		FuncionalidadVIEW2 view = new FuncionalidadVIEW2();
		view.setVisible(true);
	}
}
