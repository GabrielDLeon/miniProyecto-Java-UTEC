package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import edu.utec.uy.model.Funcionalidad;
import edu.utec.uy.utils.DB;

public class FuncRolDAO implements IFuncRolDAO {

	private Connection connection = DB.getConnection();
	private String msg = "";
	private static final String
	INSERT = "INSERT INTO ROL_FUNCION(ID_ROL_FUNCION, ID_ROL, ID_FUNCION) VALUES (ROL_FUNC_SEQ.NEXTVAL,?,?)",
	DELETE = "DELETE FROM ROL_FUNCION WHERE id_rol = ? AND id_funcion = ?",
	SELECT = "SELECT id_funcionalidad, nombre, descripcion FROM FUNCIONALIDAD INNER JOIN ROL_FUNCION ON FUNCIONALIDAD.id_funcionalidad = ROL_FUNCION.id_funcion WHERE id_rol = ? ORDER BY FUNCIONALIDAD.id_funcionalidad";
	
	public String insert(int idRol, int idFuncionalidad) {
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setInt(1, idRol);
			statement.setInt(2, idFuncionalidad);
			statement.executeUpdate();
			statement.close();
			msg = "SE HA AGREGADO LA FUNCIONALIDAD AL ROL CORRECTAMENTE!";
		} catch (Exception e) {
			msg = "NO SE PUDO AGREGAR LA FUNCIONALIDAD AL ROL";
			e.printStackTrace();
		}
		return msg;
	}

	public String delete(int idRol, int idFuncionalidad) {
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setInt(1, idRol);
			statement.setInt(2, idFuncionalidad);
			statement.executeUpdate();
			statement.close();
			msg = "SE HA QUITADO LA FUNCIONALIDAD DEL ROL CORRECTAMENTE!";
		} catch (Exception e) {
			msg = "NO SE PUDO QUITAR LA FUNCIONALIDAD DEL ROL";
			e.printStackTrace();
		}
		return msg;
	}

	public LinkedList<Funcionalidad> getList(int idRol) {
		LinkedList<Funcionalidad> lista = new LinkedList<Funcionalidad>();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT);
			statement.setInt(1, idRol);
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
