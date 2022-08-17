package miniProyecto;

import java.sql.Connection;

import database.DB;
import controllers.FuncionalidadController;


public class App {
	public static void main(String[] args) {
		if (checkDrivers()) {
			System.out.println("Iniciando aplicación...");
			String db = "jdbc:oracle:thin:@localhost:1521:xe", user = "C##JAVA", password = "java";
			DB database = new DB(db, user, password);
			workspace(database.getConnection());
			database.closeConnection();
		}
	}

	private static void workspace(Connection connection) {
		System.out.println("Iniciando Workspace...");
		FuncionalidadController cFuncionalidad = FuncionalidadController.getInstance(connection);
		cFuncionalidad.insertar();
		cFuncionalidad.listar();
		cFuncionalidad.eliminar();
		cFuncionalidad.listar();
	}

	private static boolean checkDrivers() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Se encontró el Driver para Oracle DataBase");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
