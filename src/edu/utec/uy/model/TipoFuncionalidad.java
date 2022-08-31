package edu.utec.uy.model;

public enum TipoFuncionalidad {
	CONTROL_INVENTARIO ("Control de Inventario", "Descripción de la funcionalidad "),
	VENTAS ("Manejo de Ventas", "Descripción de la funcionalidad Manejo de Ventas"),
	COMPRAS ("Manejo de Compras", "Descripción de la funcionalidad Manejo de Compras"),
	CUENTA_CORRIENTE ("Manejo de Cuenta Corriente", "Descripción de la funcionalidad Manejo de Cuenta corriente"),
	SUELDOS ("Manejo de Sueldos", "Descripción de la funcionalidad Manejo de Sueldos");
	
	private String nombre, descripcion;

	private TipoFuncionalidad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
