package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.PersonaDAO;
import edu.utec.uy.model.Persona;

public class PersonaBO {

	private String mensaje = "";
	private PersonaDAO dao = new PersonaDAO();

	public String login(String mail, String password) {
		try {
			mensaje = dao.login(mail, password);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}
	
	public String agregarPersona(Persona instancia) {
		try {
			mensaje = dao.insert(instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public String modificarPersona(Persona instancia) {
		try {
			mensaje = dao.update(instancia);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public String eliminarPersona(int id) {
		try {
			mensaje = dao.delete(id);
		} catch (Exception e) {
			mensaje += "" + e.getMessage();
		}
		return mensaje;
	}

	public LinkedList<Persona> listarPersona() {
		LinkedList<Persona> lista = dao.getList();
		return lista;
	}
}
