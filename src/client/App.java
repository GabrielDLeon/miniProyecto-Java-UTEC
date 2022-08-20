package client;

import edu.utec.uy.bo.PersonaBO;
import edu.utec.uy.view.FuncionalidadVIEW;

public class App {
	public static void main(String[] args) {
		System.out.println("Inicializando aplicación...");
		
		PersonaBO pBO = new PersonaBO();

		FuncionalidadVIEW view = new FuncionalidadVIEW();
		view.setVisible(true);
	}
}
