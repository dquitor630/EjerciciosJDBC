package activity11;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Scanner keyboard = new Scanner(System.in);
        String classroom, type, subject, script;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        System.out.println("escribe la oferta educativa correspondiente:");
        type = keyboard.nextLine();
        System.out.println("escribe el curso");
        classroom = keyboard.nextLine();
        System.out.println("escribe la asignatura");
        subject = keyboard.nextLine();
        //SELECT * FROM horario left join tramohorario on horario.codTramo = tramohorario.codTramo where codOe = "DAM" and codAsig = "PROG" and codCurso = "1A";
        script =  "SELECT * FROM horario left join tramohorario on horario.codTramo = tramohorario.codTramo where codOe = \"" + type +"\" and codAsig = \"" + subject + "\" and codCurso = \"" + classroom +"\";";
        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("%s %s %s %s \n", rs.getString("codTramo"), rs.getString("horaInicio"), rs.getString("horaFin"), rs.getString("dia"));
        }

    }
}
