package activity8;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        String script = "        SELECT profesor.*, IFNULL(curso.codCurso, 'Este profesor no es tutor')\n" +
                "        FROM profesor\n" +
                "        LEFT JOIN curso ON curso.codTutor = profesor.codProf\n" +
                "        GROUP BY profesor.codProf;";
        Statement statement = connection.createStatement();
        boolean value = statement.execute(script);
        if (value) {
            ResultSet rs = statement.getResultSet();
            while (rs.next()){
                System.out.printf("%s %s %s %s %s \n", rs.getString(1),rs.getString(2), rs.getString(3), rs.getDate(4).toString(), rs.getString(5));
            }
            rs.close();
        }
        statement.close();
        connection.close();
    }
}
