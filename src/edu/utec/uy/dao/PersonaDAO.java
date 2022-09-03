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
import edu.utec.uy.vo.PersonaVO;

public class PersonaDAO {

	private Connection connection = DB.getConnection();
	private String mensaje = "";

	private static final String SELECT = "SELECT * FROM PERS_ROL_VIEW",
			LOGIN = "SELECT * FROM PERS_ROL_VIEW WHERE mail = ?",
			INSERT = "INSERT INTO PERSONA (id_persona, documento, nombre1, nombre2, apellido1, apellido2, mail, clave, id_rol, fec_nac) VALUES (PERSONA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			UPDATE = "UPDATE PERSONA SET documento = ?, nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, mail = ?, clave = ?, fec_nac = ?, id_rol = ? WHERE id_persona = ?",
			DELETE = "DELETE FROM PERSONA WHERE id_persona = ?",
			SEARCH = "SELECT * FROM PERS_ROL_VIEW WHERE nombre1 LIKE ? OR nombre2 LIKE ? OR apellido1 LIKE ? OR apellido2 LIKE ? ";

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

	public String insert(Persona i) {
		System.out.println(i.getFechaNac());
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, i.getDocumento());
			statement.setString(2, i.getNombre1());
			statement.setString(3, i.getNombre2());
			statement.setString(4, i.getApellido1());
			statement.setString(5, i.getApellido2());
			statement.setString(6, i.getMail());
			statement.setString(7, i.getClave());
			statement.setInt(8, i.getRol().getId());
			statement.setDate(9, i.getFechaNac());
			statement.execute();
			statement.close();
			mensaje = "PERSONA INSERTADA CORRECTAMENTE";
		} catch (SQLException e) {
			mensaje = "NO SE PUDO INSERTAR LA PERSONA\n" + e.getMessage();
			e.printStackTrace();
		}
		return mensaje;
	}

	public String update(Persona i) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setString(1, i.getDocumento());
			statement.setString(2, i.getNombre1());
			statement.setString(3, i.getNombre2());
			statement.setString(4, i.getApellido1());
			statement.setString(5, i.getApellido2());
			statement.setString(6, i.getMail());
			statement.setString(7, i.getClave());
			statement.setDate(8, i.getFechaNac());
			statement.setInt(9, i.getRol().getId());
			statement.setInt(10, i.getId());
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

	public LinkedList<Persona> getList(String filtro) {
		LinkedList<Persona> lista = new LinkedList<Persona>();
		try {
			boolean useFiltro = !filtro.isEmpty();
			String query = (useFiltro) ? SEARCH : SELECT;
			PreparedStatement statement = connection.prepareStatement(query);
			if (useFiltro) {
				int cant = 4;
				for (int i = 1; i <= cant; i++) {
					statement.setString(i, "%" + filtro + "%");
				}
			}

			ResultSet rs = statement.executeQuery();

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
				Rol rol = Rol.createRol(TipoRol.valueOf(tipo));
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