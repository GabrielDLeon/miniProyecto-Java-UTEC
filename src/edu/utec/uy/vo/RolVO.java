package edu.utec.uy.vo;

import edu.utec.uy.model.TipoRol;

public class RolVO {
	private int id;
	private String nombre, descripcion;
	private TipoRol tipo;

	public RolVO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoRol getTipo() {
		return tipo;
	}

	public void setTipo(TipoRol tipo) {
		this.tipo = tipo;
	}

}
