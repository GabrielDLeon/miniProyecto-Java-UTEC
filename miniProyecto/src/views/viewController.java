package views;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class viewController {

    public static void showData(ResultSet resultSet) {
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			if (!resultSet.next()) {
				System.out.println("No se encontraron registros en el resultado obtenido.");
			} else {
				do {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1)
							System.out.print(",  ");
						String columnValue = resultSet.getString(i);
						System.out.print(columnValue);
					}
					System.out.println();
				} while (resultSet.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
