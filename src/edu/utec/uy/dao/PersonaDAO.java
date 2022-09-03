package edu.utec.uy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import edu.utec.uy.model.Persona;
import edu.utec.uy.model.Rol;
import edu.utec.uy.model.RolAdministrador;
import edu.utec.uy.model.RolJefe;
import edu.utec.uy.model.RolOperador;
import edu.utec.uy.model.TipoRol;
import edu.utec.uy.utils.DB;

public class PersonaDAO {

	private Connection connection = DB.getConnection();
	private String mensaje = "";

	private static final String SELECT = "SELECT * FROM PERS_ROL_VIEW",
			LOGIN = "SELECT * FROM PERS_ROL_VIEW WHERE mail = ?",
			INSERT = "INSERT INTO PERSONA (id_persona, documento, nombre1, nombre2, apellido1, apellido2, mail, clave, id_rol, fec_nac) VALUES (PERSONA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			UPDATE = "UPDATE PERSONA SET documento = ?, nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, mail = ?, clave = ?, fec_nac = ?, id_rol = ? WHERE id_persona = ?",
			DELETE = "DELETE FROM PERSONA WHERE id_persona = ?";

	public Persona login(String mail, String clave) {
		try {
			PreparedStatement statement = connection.prepareStatement(LOGIN);
			statement.setString(1, mail);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (clave.equals(rs.getString("CLAVE"))) {
					Persona p = getPersonaFromRS(rs);
					return p;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String insert(Persona persona) {
		System.out.println(persona.getFechaNac());
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, persona.getDocumento());
			statement.setString(2, persona.getNombre1());
			statement.setString(3, persona.getNombre2());
			statement.setString(4, persona.getApellido1());
			statement.setString(5, persona.getApellido2());
			statement.setString(6, persona.getMail());
			statement.setString(7, persona.getClave());
			statement.setInt(8, persona.getRol().getId());
			statement.setDate(9, persona.getFechaNac());
			statement.execute();
			statement.close();
			mensaje = "PERSONA INSERTADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR LA PERSONA\n" + e.getMessage();
			e.printStackTrace();
		}
		return mensaje;
	}

	public String update(Persona persona) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setString(1, persona.getDocumento());
			statement.setString(2, persona.getNombre1());
			statement.setString(3, persona.getNombre2());
			statement.setString(4, persona.getApellido1());
			statement.setString(5, persona.getApellido2());
			statement.setString(6, persona.getMail());
			statement.setString(7, persona.getClave());
			statement.setDate(8, persona.getFechaNac());
			statement.setInt(9, persona.getRol().getId());
			statement.setInt(10, persona.getId());
			statement.execute();
			statement.close();
			mensaje = "PERSONA MODIFICADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO MODIFICAR LA PERSONA\n" + e.getMessage();
		}

		return mensaje;
	}

	public String delete(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			mensaje = "PERSONA ELIMINADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO ELIMINAR LA PERSONA\n" + e.getMessage();
		}
		return mensaje;
	}

	public LinkedList<Persona> getList() {
		LinkedList<Persona> lista = new LinkedList<Persona>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);
			while (rs.next()) {
				Persona p = getPersonaFromRS(rs);
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Persona getPersonaFromRS(ResultSet rs) {
		Persona p = new Persona();
		try {
			p.setId(rs.getInt("id_persona"));
			p.setClave(rs.getString("clave"));
			p.setDocumento(rs.getString("documento"));
			p.setNombre1(rs.getString("nombre1"));
			p.setNombre2(rs.getString("nombre2"));
			p.setApellido1(rs.getString("apellido1"));
			p.setApellido2(rs.getString("apellido2"));
			p.setMail(rs.getString("mail"));
			p.setFechaNac(rs.getDate("fec_nac"));

			String tipo = rs.getString("tipo_rol");
			if (tipo != null) {
				Rol rol = null;
				if (tipo.equals(TipoRol.ADMINISTRADOR.toString())) {
					rol = new RolAdministrador();
				} else if (tipo.equals(TipoRol.JEFE_SECCION.toString())) {
					rol = new RolJefe();
					rol.setTipo(TipoRol.JEFE_SECCION);
				} else if (tipo.equals(TipoRol.OPERADOR_SECCION.toString())) {
					rol = new RolOperador();
				}
				rol.setId(rs.getInt("id_rol"));
				rol.setNombre(rs.getString("rol"));
				rol.setDescripcion(rs.getString("descripcion"));
				p.setRol(rol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}