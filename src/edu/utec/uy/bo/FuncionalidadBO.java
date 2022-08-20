package edu.utec.uy.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

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
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarFuncionalidad(Funcionalidad instancia) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.modificarFuncionalidad(connection, instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarFuncionalidad(int id) {
		try {
			Connection connection = DB.getConnection();
			mensaje = DAO.eliminarFuncionalidad(connection, id);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public ArrayList<Funcionalidad> listarFuncionalidad() {
		ArrayList<Funcionalidad> lista = new ArrayList<Funcionalidad>();		
		try {
			Connection connection = DB.getConnection();
			lista = DAO.listarFuncionalidad(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
