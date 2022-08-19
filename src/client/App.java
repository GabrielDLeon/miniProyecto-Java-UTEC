package client;

import edu.utec.uy.bo.PersonaBO;
import edu.utec.uy.view.RolVIEW;

public class App {
	public static void main(String[] args) {
		System.out.println("Inicializando aplicación...");
		
		PersonaBO pBO = new PersonaBO();
		
		String msg = pBO.login("pacudeus@gmail.com", "123");
		System.out.println(msg);
		
		RolVIEW view = new RolVIEW();
		view.setVisible(true);
		
	}
}
