package activity2;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Main().app();
    }

    public void app() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        DatabaseMetaData dbmd = connection.getMetaData();
        System.out.println("tabla asignaturas:");
        System.out.println("Claves primarias:");
        printResult(dbmd.getPrimaryKeys(null, null, "asignatura"));
        System.out.println("Claves ajenas que utilizan la clave de esta tabla:");
        printResult(dbmd.getExportedKeys(null, null, "asignatura"));
        System.out.println("Claves ajenas:");
        printResult(dbmd.getImportedKeys(null, null, "asignatura"));
        System.out.println("columnas:");
        printResult(dbmd.getColumns(null, null, "asignatura", null));
    }

    void printResult(ResultSet result) throws SQLException {
        while(result.next()){
            System.out.println(result.getString(4));
        }
    }
}