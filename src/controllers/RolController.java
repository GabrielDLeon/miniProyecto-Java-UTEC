package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import miniProyecto.RolAdministrador;
import miniProyecto.RolJefe;
import miniProyecto.RolOperador;
import views.viewController;

public class RolController {
	
	public static void mostrarRol(Connection connection) {
        String query = "SELECT * FROM ROL";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            viewController.showData(rs);
        } catch (Exception e) {
            e.printStackTrace();
		}
	}

	public static void eliminarRol(Connection connection) {
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String query = "DELETE FROM ROL WHERE ID_ROL = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertarRolAdministrador(Connection connection) {
        Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        String descripcion = JOptionPane.showInputDialog("Descripción: ");
        RolAdministrador rol = new RolAdministrador(nombre, descripcion);
        String query = "INSERT INTO ROL (id_rol, nombre, descripcion) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void insertarRolJefe(Connection connection) {
	    Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
	    String nombre = JOptionPane.showInputDialog("Nombre: ");
	    String descripcion = JOptionPane.showInputDialog("Descripción: ");
	    RolJefe rol = new RolJefe(nombre, descripcion);
	   String query = "INSERT INTO ROL (id_rol, nombre, descripcion) VALUES (?,?,?)";
			try {
				PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        statement.setString(2, nombre);
	        statement.setString(3, descripcion);
	        statement.execute();
	    } catch (Exception e) {
	        e.printStackTrace();
			}
		}
	
	public static void insertarRolOperador(Connection connection) {
	    Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
	    String nombre = JOptionPane.showInputDialog("Nombre: ");
	    String descripcion = JOptionPane.showInputDialog("Descripción: ");
	    RolOperador rol = new RolOperador(nombre, descripcion);
	    String query = "INSERT INTO ROL (id_rol, nombre, descripcion) VALUES (?,?,?)";
			try {
				PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        statement.setString(2, nombre);
	        statement.setString(3, descripcion);
	        statement.execute();
	    } catch (Exception e) {
	        e.printStackTrace();
			}
		}
}
