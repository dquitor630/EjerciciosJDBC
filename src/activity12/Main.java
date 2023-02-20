package activity12;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Scanner keyboard = new Scanner(System.in);
        String teacher, script;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        System.out.println("escribe el código del profesor que quieres comprobar:");
        teacher = keyboard.nextLine().toUpperCase();
        script =  "Select nombre from asignatura join reparto where asignatura.codAsig = reparto.codAsig and codProf = \"" + teacher + "\"";
        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("%s\n", rs.getString("nombre"));
        }

    }
}
