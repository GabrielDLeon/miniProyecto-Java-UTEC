package edu.utec.uy.bo;

import java.sql.Connection;

import edu.utec.uy.dao.FuncionalidadDAO;
import edu.utec.uy.db.DB;
import edu.utec.uy.entity.Funcionalidad;

public class FuncionalidadBO {

	private String mensaje = "";
	private FuncionalidadDAO DAO = new FuncionalidadDAO();
	
	public String agregarFuncionalidad(Funcionalidad instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.agregarFuncionalidad(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarFuncionalidad(Funcionalidad instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.modificarFuncionalidad(connection, instancia);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarFuncionalidad(int id) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.eliminarFuncionalidad(connection, id);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public void listarFuncionalidad() {
		try {
			Connection connection = DB.getConnection();
			DAO.listarFuncionalidad(connection);
			connection.close();
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
	}
	
}
