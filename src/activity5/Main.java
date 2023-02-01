package activity5;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Main().app();
    }

    public void app() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        String script = "Update asignatura as a left join reparto as r on a.codAsig = r.codAsig set horasTotales = horasTotales + horasTotales * 0.10 where a.nombre Like 'm%' and r.codOe = \"FPB\"";

        Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        statement.close();
        connection.close();
    }
}
