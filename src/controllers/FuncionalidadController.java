package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

import javax.swing.JOptionPane;

import miniProyecto.Funcionalidad;

import views.viewController;

public class FuncionalidadController {
	private static FuncionalidadController controller;
    public Connection connection;

    private FuncionalidadController(Connection connection) {
        this.connection = connection;
    }

    public static FuncionalidadController getInstance(Connection connection) {
        if (controller == null)
            controller = new FuncionalidadController(connection);
        return controller;
    }

	public void insertar() {
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String nombre = JOptionPane.showInputDialog("Nombre: ");
		String descripcion = JOptionPane.showInputDialog("Descripción: ");
		Funcionalidad funcionalidad = new Funcionalidad(nombre, descripcion);

		String query = "INSERT INTO FUNCIONALIDAD (id_funcionalidad, nombre, descripcion) VALUES (?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, nombre);
			statement.setString(3, descripcion);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminar() {
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String query = "DELETE FROM FUNCIONALIDAD WHERE ID_FUNCIONALIDAD = ?";
        try{
		    PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
	}

    public void listar() {
        String query = "SELECT * FROM FUNCIONALIDAD";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            viewController.showData(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
