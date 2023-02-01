package activity4;

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
        String scriptTeacher = "INSERT INTO `horario`.`profesor` (`codProf`, `nombre`, `apellidos`, `fechaAlta`) VALUES ('DAS', 'Daniel', 'Ayala Soriano', '2023-02-01');";
        String scriptClass = "INSERT INTO `horario`.`curso` (`codOe`, `codCurso`, `codTutor`) VALUES ('FPB', '1A', 'DAS');";
        String scriptSubject = "INSERT INTO `horario`.`asignatura` (`codAsig`, `nombre`, `horasSemanales`, `horasTotales`) VALUES ('OACE', 'Operaciones auxiliares para la configuración y explotación', '7', '25');\n" +
                "INSERT INTO `horario`.`asignatura` (`codAsig`, `nombre`, `horasSemanales`, `horasTotales`) VALUES ('MMSC', 'Montaje y Mantenimiento de sistemas y componentes informáticos', '9', '315');";
        Statement statement = connection.createStatement();
        statement.executeUpdate(scriptTeacher);
        statement.executeUpdate(scriptClass);
        statement.executeUpdate(scriptSubject);
        statement.close();
        connection.close();
    }
}
