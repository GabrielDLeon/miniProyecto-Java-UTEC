package edu.utec.uy.dao;

import java.util.LinkedList;

import edu.utec.uy.model.Funcionalidad;

public interface IFuncRolDAO {
	
	public String insert(int idRol, int idFuncionalidad);
	
	public String delete(int idRol, int idFuncionalidad);
	
	public LinkedList<Funcionalidad> getList(int idRol);
	
}
