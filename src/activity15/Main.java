package activity15;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

//SELECT * FROM reparto left join horario on reparto.codAsig = horario.codAsig left join tramohorario on horario.codTramo = tramohorario.codTramo where codProf = "AGL" and time("2017-06-20 10:34:00") >= horaInicio and ("2017-06-20 10:34:00") < horaFin and dia = "JUEVES";
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Scanner keyboard = new Scanner(System.in);
        String teacher, script;
        Locale locale = new Locale("es", "ES");
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        System.out.println("escribe el código del profesor:");
        teacher = keyboard.nextLine();
        script =  "SELECT * FROM reparto left join horario on reparto.codAsig = horario.codAsig " +
                "left join tramohorario on horario.codTramo = tramohorario.codTramo " +
                "where codProf = \""+ teacher +"\" and CURRENT_TIME() >= horaInicio " +
                "and CURRENT_TIME() < horaFin and dia = \""
                + LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, locale).toUpperCase() +"\";";
        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("%s %s\n", rs.getString("codOe"), rs.getString("codCurso"));
        }
    }
}
