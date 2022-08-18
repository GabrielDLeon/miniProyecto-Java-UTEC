package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.utec.uy.entity.Rol;
import edu.utec.uy.view.viewController;

public class RolDAO {

	private String mensaje = "";
	
	public String agregarRol(Connection connection, Rol rol) {
		String query = "INSERT INTO ROL (id_rol, nombre, descripcion) VALUES (ROL_SEQ.NEXTVAL,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rol.getNombre());
			statement.setString(2, rol.getDescripcion());
			statement.execute();
			statement.close();
			mensaje = "ROL INSERTADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR EL ROL\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarRol(Connection connection, Rol rol) {
		String query = "UPDATE ROL SET nombre = ?, descripcion = ? WHERE id_rol = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rol.getNombre());
			statement.setString(2, rol.getDescripcion());
			statement.setInt(3, rol.getId());
			statement.execute();
			statement.close();
			mensaje = "ROL MODIFICADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR EL ROL\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarRol(Connection connection, int id) {
		String query = "DELETE FROM ROL WHERE id_rol = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			mensaje = "ROL ELIMINADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR EL ROL\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public void listarRol(Connection connection) {
		String query = "SELECT * FROM ROL";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			viewController.showData(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
