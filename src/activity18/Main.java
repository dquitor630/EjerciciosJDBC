package activity18;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    /*
       DELIMITER //
        CREATE PROCEDURE getSubjectInfoAndHour(IN courseId VARCHAR(50), IN subjectId VARCHAR(50),OUT nombre VARCHAR(255),OUT horasSemanales INTEGER)
        BEGIN
            SELECT horasSemanales, profesor.nombre INTO horasSemanales, nombre
            FROM reparto
            left JOIN asignatura ON asignatura.codAsig = reparto.codAsig
            left JOIN profesor ON profesor.codProf = reparto.codProf
            WHERE reparto.codCurso = courseId AND reparto.codAsig = subjectId;
        END
     */
    void app() throws SQLException, ClassNotFoundException {
        String subject, course, result;
        Scanner keyboard = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        CallableStatement statement = connection.prepareCall("{call getSubjectInfoAndHour(?,?, ?, ?)}");
        statement.registerOutParameter(3, Types.VARCHAR);
        statement.registerOutParameter(4, Types.INTEGER);
        System.out.println("Introduce el curso");
        course = keyboard.nextLine();
        System.out.println("Escribe el código de asignatura");
        subject = keyboard.nextLine();
        statement.setString(1, course);
        statement.setString(2, subject);
        statement.execute();
        result = statement.getString(3);
        result += " " + statement.getInt(4);
        System.out.println(result);
    }
}
