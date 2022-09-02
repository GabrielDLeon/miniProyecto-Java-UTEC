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

	private static final String SELECT = "SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, mail, clave, fec_nac, persona.id_rol AS rol, rol.tipo as tipo_rol FROM persona INNER JOIN rol ON persona.id_rol = rol.id_rol ORDER BY id_persona",
			LOGIN = "SELECT * FROM PERSONA WHERE mail = ? AND clave = ?",
			INSERT = "INSERT INTO PERSONA (id_persona, documento, nombre1, nombre2, apellido1, apellido2, mail, clave, id_rol, fec_nac) VALUES (PERSONA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			UPDATE = "UPDATE PERSONA SET documento = ?, nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?,mail = ?, clave = ?, fec_nac = ? WHERE id_persona = ?",
			DELETE = "DELETE FROM PERSONA WHERE id_persona = ?";

	public String login(String mail, String password) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(LOGIN);
			while (result.next()) {
				String clave = result.getString("CLAVE");
				if (password.equals(clave))
					mensaje = "Inicio de sesion correcto";
				else
					mensaje = "Datos invalidos";
			}
		} catch (SQLException e) {
			mensaje = "No fue posible acceder al sistema";
			e.printStackTrace();
		}
		return mensaje;
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
			statement.setInt(9, persona.getId());
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
				Persona p = new Persona();
				p.setId(rs.getInt("id_persona"));
				p.setDocumento(rs.getString("documento"));
				p.setNombre1(rs.getString("nombre1"));
				p.setNombre2(rs.getString("nombre2"));
				p.setApellido1(rs.getString("apellido1"));
				p.setApellido2(rs.getString("apellido2"));
				p.setMail(rs.getString("mail"));
				p.setFechaNac(rs.getDate("fec_nac"));
				
				String tipo = rs.getString("tipo_rol").toUpperCase();
				
				if (tipo.equals(TipoRol.ADMINISTRADOR.toString())){
				    RolAdministrador rol = new RolAdministrador();
				    rol.setId(rs.getInt("rol"));
				    rol.setTipo(TipoRol.ADMINISTRADOR);
				    p.setRol(rol);
				} else if (tipo.equals(TipoRol.JEFE_SECCION.toString())){
				    RolJefe rol = new RolJefe();
				    rol.setId(rs.getInt("rol"));
				    rol.setTipo(TipoRol.JEFE_SECCION);
				    p.setRol(rol);
				} else if (tipo.equals(TipoRol.OPERADOR_SECCION.toString())){
				    RolOperador rol = new RolOperador();
				    rol.setId(rs.getInt("rol"));
				    rol.setTipo(TipoRol.OPERADOR_SECCION);
				    p.setRol(rol);
				}
				
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}