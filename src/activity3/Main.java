package activity3;
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
        //he modificado el enum de la base de datos para añadir un nuevo tipo.
        String script = "INSERT INTO `horario`.`ofertaeducativa` (`codOe`, `nombre`, `descripcion`, `tipo`, `fechaLey`) VALUES ('FPB', 'FP Básica Informática y comunicaciones', 'La formación profesional básica de informática y comunicaciones tiene una', 'FPB', '2011-06-16 00:00:00')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        statement.close();
        connection.close();
    }
}
