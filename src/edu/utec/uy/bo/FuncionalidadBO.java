package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.FuncionalidadDAO;
import edu.utec.uy.model.Funcionalidad;

public class FuncionalidadBO {

	private String mensaje = "";
	private FuncionalidadDAO dao = new FuncionalidadDAO();

	public String agregarFuncionalidad(Funcionalidad instancia) {
		String nombre = instancia.getNombre();
		String descripcion = instancia.getDescripcion();

		if (nombre.isEmpty() || descripcion.isEmpty())
			return "Debes completar todos los campos";

		try {
			mensaje = dao.insert(instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public String modificarFuncionalidad(Funcionalidad instancia) {
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

	public String eliminarFuncionalidad(int id) {		
		try {
			mensaje = dao.delete(id);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public LinkedList<Funcionalidad> listarFuncionalidad() {
		LinkedList<Funcionalidad> lista = dao.getList();
		return lista;
	}

}
