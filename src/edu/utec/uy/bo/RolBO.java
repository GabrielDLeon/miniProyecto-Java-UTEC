package edu.utec.uy.bo;

import java.sql.Connection;

import edu.utec.uy.dao.RolDAO;
import edu.utec.uy.db.DB;
import edu.utec.uy.entity.Rol;

public class RolBO {
	
	private String mensaje;
	private RolDAO DAO = new RolDAO();
	
	public String agregarRol(Rol instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.agregarRol(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarRol(Rol instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.modificarRol(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarRol(int id) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.eliminarRol(connection, id);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public void listarFuncionalidad() {
		try {
			Connection connection = DB.getConnection();
			DAO.listarRol(connection);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
	}
	
}
