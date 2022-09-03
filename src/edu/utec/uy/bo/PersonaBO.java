package edu.utec.uy.bo;

import java.util.LinkedList;

import edu.utec.uy.dao.PersonaDAO;
import edu.utec.uy.model.Persona;
import edu.utec.uy.model.Rol;
import edu.utec.uy.vo.PersonaVO;

public class PersonaBO {

	private String mensaje = "";
	private PersonaDAO dao = new PersonaDAO();

	public Persona login(String mail, String clave) {
		if (mail.isEmpty() || clave.isEmpty()) {
			return null;
		}
		Persona p = dao.login(mail, clave);
		return p;
	}

	public String insert(PersonaVO i) {
		if (i.getDocumento().isEmpty() || i.getNombre1().isEmpty() || i.getApellido1().isEmpty() || i.getMail().isEmpty()) {
			return "DEBE INGRESAR TODO LOS CAMPOS";
		}

		Persona p = getPersonaFromVO(i);
		mensaje = dao.insert(p);
		return mensaje;
	}

	public String update(PersonaVO i) {
		if (i.getDocumento().isEmpty() || i.getNombre1().isEmpty() || i.getApellido1().isEmpty() || i.getMail().isEmpty()) {
			return "DEBE INGRESAR TODO LOS CAMPOS";
		}
		
		Persona p = getPersonaFromVO(i);
		p.setId(i.getIdPersona());
		mensaje = dao.update(p);
		return mensaje;
	}

	public String delete(int id) {
		mensaje = dao.delete(id);
		return mensaje;
	}

	public LinkedList<Persona> getList(String filtro) {
		LinkedList<Persona> lista = dao.getList(filtro);
		return lista;
	}
	
	private Persona getPersonaFromVO(PersonaVO i) {
		Rol rol = Rol.createRol(i.getTipoRol());
		rol.setId(i.getIdRol());
		rol.setDescripcion(i.getDescripcionRol());
		rol.setNombre(i.getNombreRol());
		
		Persona p = new Persona();
		p.setDocumento(i.getDocumento());
		p.setMail(i.getMail());
		p.setClave(i.getClave());
		p.setNombre1(i.getNombre1());
		p.setNombre2(i.getNombre2());
		p.setApellido1(i.getApellido1());
		p.setApellido2(i.getApellido2());	
		p.setFechaNac(i.getFechaNac());
		p.setRol(rol);
		
		return p;
	}
}
