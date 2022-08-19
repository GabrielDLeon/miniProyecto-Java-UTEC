package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.utec.uy.entity.Persona;
import edu.utec.uy.view.viewController;

public class PersonaDAO {

	private String mensaje = "";

	public String login(Connection connection, String mail, String password) {
		String query = "SELECT * FROM PERSONA WHERE mail = '"+mail+"'";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				String clave = result.getString("CLAVE");
				if (password.equals(clave)) mensaje = "Inicio de sesion correcto";
				else mensaje = "Datos invalidos";
			}
		} catch (SQLException e) {
			mensaje = "No fue posible acceder al sistema";
			e.printStackTrace();
		}
		return mensaje;
	}

	public String agregarPersona(Connection connection, Persona persona) {
		String query = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2, MAIL, CLAVE, ID_ROL, FECHA_NAC) VALUES (PERSONA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, persona.getDocumento());
			statement.setString(2, persona.getApellido1());
			statement.setString(3, persona.getApellido2());
			statement.setString(4, persona.getNombre1());
			statement.setString(5, persona.getNombre2());
			statement.setString(6, persona.getMail());
			statement.setString(7, persona.getClave());
			statement.setInt(8, persona.getRol().getId());
			statement.setDate(9, (Date) null);
			statement.execute();
			statement.close();
			mensaje = "PERSONA INSERTADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR LA PERSONA\n" + e.getMessage();
			e.printStackTrace();
		}
		return mensaje;
	}

	public String modificarPersona(Connection connection, Persona persona) {
		String query = "UPDATE PERSONA SET DOCUMENTO  = ?, APELLIDO1 = ?, APELLIDO2 = ?, NOMBRE1 = ?, NOMBRE2 = ?, MAIL = ?, CLAVE = ?, FECHA_NAC = ?";

		try {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, persona.getDocumento());
			statement.setString(2, persona.getApellido1());
			statement.setString(3, persona.getApellido2());
			statement.setString(4, persona.getNombre1());
			statement.setString(5, persona.getNombre2());
			statement.setString(6, persona.getMail());
			statement.setString(7, persona.getClave());
			statement.setDate(8, (Date) persona.getFechaNac());
			statement.execute();
			statement.close();
			mensaje = "PERSONA MODIFICADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR LA PERSONA\n" + e.getMessage();
		}

		return mensaje;
	}

	public String eliminarPersona(Connection connection, int id) {
		String query = "DELETE FROM PERSONA WHERE id_persona = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			mensaje = "PERSONA ELIMINADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR LA PERSONA\n" + e.getMessage();
		}
		return mensaje;
	}

	public void listarPersona(Connection connection) {
		String query = "SELECT * FROM PERSONA";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			viewController.showData(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}