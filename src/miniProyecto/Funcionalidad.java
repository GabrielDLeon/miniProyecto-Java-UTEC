package miniProyecto;

import java.util.LinkedList;


public class Funcionalidad {
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
		return "Funcionalidad [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
