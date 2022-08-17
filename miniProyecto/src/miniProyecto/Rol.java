package miniProyecto;

public abstract class Rol {
    private String nombre, descripcion;

    public Rol() {
		super();
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

	public Rol(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
