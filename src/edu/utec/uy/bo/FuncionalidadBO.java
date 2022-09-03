package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.FuncionalidadDAO;
import edu.utec.uy.model.Funcionalidad;
import edu.utec.uy.vo.FuncionalidadVO;

public class FuncionalidadBO {

	private String mensaje = "";
	private FuncionalidadDAO dao = new FuncionalidadDAO();
	
	public String insert(FuncionalidadVO i) {
		String nombre = i.getNombre();
		String descripcion = i.getDescripcion();

		if (nombre.isEmpty() || descripcion.isEmpty()) {
			return "Debes completar todos los campos";			
		}
		
		Funcionalidad f = new Funcionalidad();
		f.setNombre(nombre);
		f.setDescripcion(descripcion);

		mensaje = dao.insert(f);
		return mensaje;
	}

	public String update(FuncionalidadVO i) {
		String nombre = i.getNombre();
		String descripcion = i.getDescripcion();
		
		if (nombre.isEmpty() || descripcion.isEmpty()) {
			return "Debes completar todos los campos";			
		}
		
		Funcionalidad f = new Funcionalidad();
		f.setId(i.getId());
		f.setNombre(nombre);
		f.setDescripcion(descripcion);
		
		mensaje = dao.update(f);
		return mensaje;
	}

	public String delete(int id) {		
		mensaje = dao.delete(id);
		return mensaje;
	}

	public LinkedList<Funcionalidad> getList(String filter) {
		LinkedList<Funcionalidad> lista = dao.getList(filter);
		return lista;
	}

}
