package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.RolDAO;
import edu.utec.uy.model.Rol;
import edu.utec.uy.vo.RolVO;

public class RolBO {
	
	private String mensaje;
	private RolDAO dao = new RolDAO();
	
	public String insert(RolVO i) {
		String nombre = i.getNombre();
		String descripcion = i.getDescripcion();
		
		if (nombre.isEmpty() || descripcion.isEmpty()) {
			return "Debes completar todos los campos";			
		}
		
		Rol rol = Rol.createRol(i.getTipo());
		rol.setNombre(nombre);
		rol.setDescripcion(descripcion);
		
		mensaje = dao.insert(rol);
		return mensaje;
	}
	
	public String update(RolVO i) {
		String nombre = i.getNombre();
		String descripcion = i.getDescripcion();
		
		if (nombre.isEmpty() || descripcion.isEmpty()) {
			return "Debes completar todos los campos";			
		}
		
		Rol rol = Rol.createRol(i.getTipo());
		rol.setId(i.getId());
		rol.setNombre(nombre);
		rol.setDescripcion(descripcion);
		
		mensaje = dao.update(rol);
		return mensaje;
	}
	
	public String delete(int id) {
		mensaje = dao.delete(id);
		return mensaje;
	}
	
	public LinkedList<Rol> getList(String filtro) {
		LinkedList<Rol> lista = dao.getList(filtro);
		return lista;
	}
	
}
