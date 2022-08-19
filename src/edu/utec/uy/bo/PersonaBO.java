package edu.utec.uy.bo;

import java.sql.Connection;

import edu.utec.uy.dao.PersonaDAO;
import edu.utec.uy.db.DB;
import edu.utec.uy.entity.Persona;

public class PersonaBO {

	private String mensaje;
	private PersonaDAO DAO = new PersonaDAO();

	public String login(String mail, String password) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.login(connection, mail, password);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String agregarPersona(Persona instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.agregarPersona(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public String modificarPersona(Persona instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.modificarPersona(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public String eliminarPersona(int id) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.eliminarPersona(connection, id);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public void listarPersona() {
		try {
			Connection connection = DB.getConnection();
			DAO.listarPersona(connection);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
	}
}
