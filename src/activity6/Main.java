package activity6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Main().app();
    }

    public void app() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        String script = "DELETE FROM `horario`.`ofertaeducativa` WHERE (`codOe` = 'FPB');";
        //la base de datos esta configurada para borrar en cascada así que no hace falta más
        Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        statement.close();
        connection.close();
    }
}
