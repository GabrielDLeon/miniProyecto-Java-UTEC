package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.utec.uy.entity.Funcionalidad;
import edu.utec.uy.view.viewController;

public class FuncionalidadDAO {
	
	private String mensaje = "";
	
	public String agregarFuncionalidad(Connection connection, Funcionalidad funcionalidad) {
		String query = "INSERT INTO FUNCIONALIDAD (id_funcionalidad, nombre, descripcion) VALUES (FUNCIONALIDAD_SEQ.NEXTVAL,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, funcionalidad.getNombre());
			statement.setString(2, funcionalidad.getDescripcion());
			statement.execute();
			statement.close();
			mensaje = "FUNCIONALIDAD INSERTADA CORRECTAMENTE";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "NO SE PUDO INSERTAR LA FUNCIONALIDAD\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarFuncionalidad(Connection connection, Funcionalidad funcionalidad) {
		String query = "UPDATE FUNCIONALIDAD SET nombre = ?, descripcion = ? WHERE id_funcionalidad = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, funcionalidad.getNombre());
			statement.setString(2, funcionalidad.getDescripcion());
			statement.setInt(3, funcionalidad.getId());
			statement.execute();
			statement.close();
			mensaje = "FUNCIONALIDAD MODIFICADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR LA FUNCIONALIDAD\n"+e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarFuncionalidad(Connection connection, int id) {
		String query = "DELETE FROM FUNCIONALIDAD WHERE id_funcionalidad = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			mensaje = "FUNCIONALIDAD ELIMINADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR LA FUNCIONALIDAD\n"+e.getMessage();
		}
		return mensaje;
	}
	

	public ArrayList<Funcionalidad> listarFuncionalidad(Connection connection) {
		ArrayList<Funcionalidad> rsAL = new ArrayList<Funcionalidad>();
		String query = "SELECT * FROM FUNCIONALIDAD ORDER BY id_funcionalidad";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			//viewController.showData(rs);
			while(rs.next()){
				Funcionalidad f = new Funcionalidad();
				f.setId(rs.getInt("ID_FUNCIONALIDAD"));
				f.setNombre(rs.getString("NOMBRE"));
				f.setDescripcion(rs.getString("DESCRIPCION"));
				rsAL.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsAL;
	}
}
