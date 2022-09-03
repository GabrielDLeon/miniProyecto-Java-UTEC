package edu.utec.uy.vo;

import java.sql.Date;

import edu.utec.uy.model.Rol;
import edu.utec.uy.model.TipoRol;

public class PersonaVO {
	private int idPersona;
	private String documento, apellido1, apellido2, nombre1, nombre2, mail, clave;
	private Date fechaNac;
	private int idRol;
	private String nombreRol, descripcionRol;
	private TipoRol tipoRol;

	public TipoRol getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(TipoRol tipoRol) {
		this.tipoRol = tipoRol;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public PersonaVO() {
		super();
	}

	@Override
	public String toString() {
		return "PersonaVO [idPersona=" + idPersona + ", documento=" + documento + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", mail=" + mail
				+ ", clave=" + clave + ", fechaNac=" + fechaNac + ", idRol=" + idRol + ", nombreRol=" + nombreRol
				+ ", descripcionRol=" + descripcionRol + "]";
	}
}
