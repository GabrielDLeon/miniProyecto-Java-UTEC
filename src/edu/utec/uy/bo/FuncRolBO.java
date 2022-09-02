package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.FuncRolDAO;
import edu.utec.uy.model.Funcionalidad;

public class FuncRolBO {
	
	private String mensaje = "";
	private FuncRolDAO dao = new FuncRolDAO();

	public String insert(int idRol, int idFuncionalidad) {
		mensaje = dao.insert(idRol, idFuncionalidad);
		return mensaje;
	}

	public String delete(int idRol, int idFuncionalidad) {
		mensaje = dao.delete(idRol, idFuncionalidad);
		return mensaje;
	}

	public LinkedList<Funcionalidad> getList(int idRol) {
		LinkedList<Funcionalidad> lista = dao.getList(idRol);
		return lista;
	}

}
