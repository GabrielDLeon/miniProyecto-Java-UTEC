package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.RolDAO;
import edu.utec.uy.model.Rol;
import edu.utec.uy.model.TipoRol;

public class RolBO {
	
	private String mensaje;
	private RolDAO dao = new RolDAO();
	
	public String agregarRol(Rol instancia) {
		String nombre = instancia.getNombre();
		String descripcion = instancia.getDescripcion();
		TipoRol tipo = instancia.getTipo();
		
		if (nombre.isEmpty() || descripcion.isEmpty())
			return "Debes completar todos los campos";
		
		try {
			mensaje = dao.insert(instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String modificarRol(Rol instancia) {
		String nombre = instancia.getNombre();
		String descripcion = instancia.getDescripcion();
		
		if (nombre.isEmpty() || descripcion.isEmpty())
			return "Debes completar todos los campos";
		
		try {
			mensaje = dao.update(instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String eliminarRol(int id) {
		try {
			mensaje = dao.delete(id);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public LinkedList<Rol> listarRol() {
		LinkedList<Rol> lista = dao.getList();
		return lista;
	}
	
}
