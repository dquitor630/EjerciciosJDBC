package activity14;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Scanner keyboard = new Scanner(System.in);
        String hour, teacher, script;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        System.out.println("escribe el código del profesor:");
        teacher = keyboard.nextLine();
        System.out.println("escribe el código del tramo horario");
        hour = keyboard.nextLine();
        script =  "SELECT * FROM reparto left join horario on reparto.codAsig = horario.codAsig left join tramohorario on horario.codTramo = tramohorario.codTramo where codProf = \"" + teacher + "\" and tramohorario.codTramo = \"" + hour +"\";";
        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("%s %s\n", rs.getString("codOe"), rs.getString("codCurso"));
        }
    }
}
