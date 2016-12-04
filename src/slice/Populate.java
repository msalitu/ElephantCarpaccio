package slice;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Populate {

	public static void main(String[] args) {
		Connection c = null;
		try {

			deleteDB();

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:carpaccio.db");

			executeStatement(c, "CREATE TABLE PRODUCTOS (NOMBRE VARCHAR(22), PRECIO DOUBLE)");
			executeStatement(c, "INSERT INTO PRODUCTOS (NOMBRE, PRECIO) VALUES ('NARANJA', 2)");
			executeStatement(c, "INSERT INTO PRODUCTOS (NOMBRE, PRECIO) VALUES ('PERA', 1)");
			executeStatement(c, "INSERT INTO PRODUCTOS (NOMBRE, PRECIO) VALUES ('MANZANA', 5)");

			executeStatement(c, "CREATE TABLE PAISES (NOMBRE VARCHAR(22), IVA DOUBLE)");
			executeStatement(c, "INSERT INTO PAISES (NOMBRE, IVA) VALUES ('UT', 6.85)");
			executeStatement(c, "INSERT INTO PAISES (NOMBRE, IVA) VALUES ('NV', 8)");
			executeStatement(c, "INSERT INTO PAISES (NOMBRE, IVA) VALUES ('TX', 6.25)");
			executeStatement(c, "INSERT INTO PAISES (NOMBRE, IVA) VALUES ('AL', 4)");
			executeStatement(c, "INSERT INTO PAISES (NOMBRE, IVA) VALUES ('CA', 8.25)");

			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Populate DB completed successfully");
	}

	private static void executeStatement(Connection c, String sql) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	private static void deleteDB() {
		File file = new File("carpaccio.db");

		if (file.delete()) {
			System.out.println(file.getName() + " DB deleted!");
		} else {
			System.out.println("DB not found.");
		}

	}

}
