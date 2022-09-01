package edu.utec.uy.model;

import java.util.LinkedList;


public class Funcionalidad {
	private int id;
    private String nombre, descripcion;
    private LinkedList<Rol> rolLista = new LinkedList<Rol>();
    private TipoFuncionalidad tipoFuncionalidad;
    
    
    public boolean acceso(Persona p) {
        // SEGUIR EL CÓDIGO
        return true;
    }
  
    public Funcionalidad() {
		super();
	}
    
	public Funcionalidad(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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

    public void agregarRol(Rol rol) {
        rolLista.add(rol);
    }

	@Override
	public String toString() {
		return "Funcionalidad [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion;
	}

	
	
}
