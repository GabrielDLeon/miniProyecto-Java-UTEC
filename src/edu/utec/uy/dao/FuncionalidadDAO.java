package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import edu.utec.uy.model.Funcionalidad;
import edu.utec.uy.utils.DB;

public class FuncionalidadDAO {

	private Connection connection = DB.getConnection();
	private String mensaje = "";

	private static final String SELECT = "SELECT * FROM FUNCIONALIDAD ORDER BY id_funcionalidad",
			SEARCH = "SELECT * FROM FUNCIONALIDAD WHERE nombre = ? ORDER BY id_funcionalidad",
			INSERT = "INSERT INTO FUNCIONALIDAD (id_funcionalidad, nombre, descripcion) VALUES (FUNCIONALIDAD_SEQ.NEXTVAL, ?,?)",
			UPDATE = "UPDATE FUNCIONALIDAD SET nombre = ?, descripcion = ? WHERE id_funcionalidad = ?",
			DELETE = "DELETE FROM FUNCIONALIDAD WHERE id_funcionalidad = ?";

	public String insert(Funcionalidad funcionalidad) {
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, funcionalidad.getNombre());
			statement.setString(2, funcionalidad.getDescripcion());
			statement.execute();
			statement.close();
			mensaje = "FUNCIONALIDAD INSERTADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR LA FUNCIONALIDAD\n" + e.getMessage();
		}
		return mensaje;
	}

	public String update(Funcionalidad funcionalidad) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setString(1, funcionalidad.getNombre());
			statement.setString(2, funcionalidad.getDescripcion());
			statement.setInt(3, funcionalidad.getId());
			statement.executeUpdate();
			statement.close();
			mensaje = "FUNCIONALIDAD MODIFICADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR LA FUNCIONALIDAD\n" + e.getMessage();
		}
		return mensaje;
	}

	public String delete(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			mensaje = "FUNCIONALIDAD ELIMINADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR LA FUNCIONALIDAD\n" + e.getMessage();
		}
		return mensaje;
	}

	public LinkedList<Funcionalidad> getList(String filter) {
		LinkedList<Funcionalidad> lista = new LinkedList<Funcionalidad>();
		String query = (filter.isEmpty()) ? SELECT : SEARCH;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			if (!filter.isEmpty()) {
				statement.setString(1, filter);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Funcionalidad f = new Funcionalidad();
				f.setId(rs.getInt("ID_FUNCIONALIDAD"));
				f.setNombre(rs.getString("NOMBRE"));
				f.setDescripcion(rs.getString("DESCRIPCION"));
				lista.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
