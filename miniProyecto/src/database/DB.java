package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private Connection connection;
	private String database, user, password;

	public DB(String database, String user, String password) {
		try {
			this.connection = DriverManager.getConnection(database, user,
					password);
			this.database = database;
			this.user = user;
			this.password = password;
			System.out.println("Conexión creada satisfactoriamente!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexión cerrada correctamente!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
