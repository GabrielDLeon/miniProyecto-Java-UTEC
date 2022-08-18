package client;

import edu.utec.uy.bo.FuncionalidadBO;
import edu.utec.uy.bo.RolBO;
import edu.utec.uy.entity.Rol;
import edu.utec.uy.entity.RolAdministrador;

public class App {
	public static void main(String[] args) {
		System.out.println("Inicializando aplicación...");
		
		// Instancias de Clases de Lógica de negocio
		FuncionalidadBO funcionalidadBO = new FuncionalidadBO();
		RolBO rolBO = new RolBO();
		
		// Creación de la instancia a insertar
		Rol rol = new RolAdministrador();
		rol.setNombre("Programador");
		rol.setDescripcion("El que programa");
		
		// Se llama al método agregarRol() del objeto rolBO
		// Este método retorna un mensaje en forma de String con el estado de la BD
		String msg = rolBO.agregarRol(rol);
		
		System.out.println(msg);
	}
}
