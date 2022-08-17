package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JOptionPane;
import views.viewController;

public class PersonaController {
	public static void mostrarPersona(Connection connection) {
		String query = "SELECT * FROM PERSONA";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			viewController.showData(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void eliminarPersona(Connection connection) {
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String query = "DELETE FROM PERSONA WHERE ID_PERSONA = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
