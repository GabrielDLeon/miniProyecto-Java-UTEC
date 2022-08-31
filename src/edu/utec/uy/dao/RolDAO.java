package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import edu.utec.uy.model.Rol;
import edu.utec.uy.utils.DB;

public class RolDAO {
	
	private Connection connection = DB.getConnection();
	private String mensaje = "";
	
	private static final String
		SELECT = "SELECT * FROM ROL ORDER BY id_rol",
		INSERT = "INSERT INTO ROL (id_rol, nombre, descripcion) VALUES (ROL_SEQ.NEXTVAL,?,?)",
		UPDATE = "UPDATE ROL SET nombre = ?, descripcion = ? WHERE id_rol = ?",
		DELETE = "DELETE FROM ROL WHERE id_rol = ?";
	
	public String insert(Rol rol) {
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
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
	
	public String update(Rol rol) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
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
	
	public String delete(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			mensaje = "ROL ELIMINADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR EL ROL\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public LinkedList<Rol> getList() {
		LinkedList<Rol> lista = new LinkedList<Rol>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);
			while(rs.next()) {
				//Rol r = new Rol();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
