package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import edu.utec.uy.model.Rol;
import edu.utec.uy.model.RolAdministrador;
import edu.utec.uy.model.RolJefe;
import edu.utec.uy.model.RolOperador;
import edu.utec.uy.utils.DB;

public class RolDAO {

	private Connection connection = DB.getConnection();
	private String mensaje = "";

	private static final String SELECT = "SELECT * FROM ROL ORDER BY id_rol",
			INSERT = "INSERT INTO ROL (id_rol, nombre, descripcion, tipo) VALUES (ROL_SEQ.NEXTVAL,?,?,?)",
			UPDATE = "UPDATE ROL SET nombre = ?, descripcion = ?, tipo = ? WHERE id_rol = ?",
			DELETE = "DELETE FROM ROL WHERE id_rol = ?",
			SEARCH = "SELECT * FROM ROL WHERE tipo = ? OR nombre = ? ORDER BY id_rol";

	public String insert(Rol rol) {
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, rol.getNombre());
			statement.setString(2, rol.getDescripcion());
			statement.setString(3, rol.getTipo().toString());
			statement.execute();
			statement.close();
			mensaje = "ROL INSERTADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR EL ROL\n" + e.getMessage();
		}
		return mensaje;
	}

	public String update(Rol rol) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setString(1, rol.getNombre());
			statement.setString(2, rol.getDescripcion());
			statement.setString(3, rol.getTipo().toString());
			statement.setInt(4, rol.getId());
			statement.execute();
			statement.close();
			mensaje = "ROL MODIFICADO CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR EL ROL\n" + e.getMessage();
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
			mensaje = "NO SE PUDO ELIMINAR EL ROL\n" + e.getMessage();
		}
		return mensaje;
	}

	public LinkedList<Rol> getList(String filtro) {
		LinkedList<Rol> lista = new LinkedList<Rol>();		
		String query = (filtro.isEmpty()) ? SELECT : SEARCH;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			if (!filtro.isEmpty()) {
				statement.setString(1, filtro);
				statement.setString(2, filtro);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String tipo = rs.getString("tipo");
				Rol r = null;
				if (tipo.equals("JEFE_SECCION")) {
					r = new RolJefe();
				} else if (tipo.equals("OPERADOR_SECCION")) {
					r = new RolOperador();
				} else if (tipo.equals("ADMINISTRADOR")) {
					r = new RolAdministrador();
				}
				r.setId(rs.getInt("id_rol"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
				lista.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
