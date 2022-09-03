package edu.utec.uy.model;

import java.util.LinkedList;

public abstract class Rol {
    protected int id;
    protected String nombre, descripcion;
    protected TipoRol tipo;
    protected LinkedList<Funcionalidad> listaFuncionalidad = new LinkedList<Funcionalidad>();

    public Rol() {
		super();
	}
    
    public static Rol createRol(TipoRol tipo) {
    	Rol rol = null;
    	if (tipo.equals(TipoRol.ADMINISTRADOR)) {
    		rol = new RolAdministrador();
    	} else if (tipo.equals(TipoRol.JEFE_SECCION)) {
    		rol = new RolJefe();
    	} else if (tipo.equals(TipoRol.OPERADOR_SECCION)) {
    		rol = new RolOperador();
    	}
		return rol;
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

	@Override
	public String toString() {
		return nombre;
	}
}
