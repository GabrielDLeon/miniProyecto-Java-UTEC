package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.PersonaDAO;
import edu.utec.uy.model.Persona;

public class PersonaBO {

	private String mensaje = "";
	private PersonaDAO dao = new PersonaDAO();

	public Persona login(String mail, String clave) {
		if (mail.isEmpty() || clave.isEmpty()){
			return null;
		}		
		Persona p = dao.login(mail, clave);
		return p;
	}
	
	public String agregarPersona(Persona instancia) {
		if (
			instancia.getDocumento().isEmpty() ||
			instancia.getNombre1().isEmpty() ||
			instancia.getApellido1().isEmpty() ||
			instancia.getMail().isEmpty()
		){
			return "DEBE INGRESAR TODO LOS CAMPOS";
		}
		mensaje = dao.insert(instancia);
		return mensaje;
	}

	public String modificarPersona(Persona instancia) {
		if (
			instancia.getDocumento().isEmpty() ||
			instancia.getNombre1().isEmpty() ||
			instancia.getApellido1().isEmpty() ||
			instancia.getMail().isEmpty()
		){
			return "DEBE INGRESAR TODO LOS CAMPOS"; 
		}
		
		mensaje = dao.update(instancia);
		return mensaje;
	}

	public String eliminarPersona(int id) {
		mensaje = dao.delete(id);
		return mensaje;
	}

	public LinkedList<Persona> listarPersona() {
		LinkedList<Persona> lista = dao.getList();
		return lista;
	}
}
