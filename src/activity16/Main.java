package activity16;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        String script = "SELECT horario.codAsig, count(Distinct codOe), count(Distinct codCurso), horasSemanales FROM horario.horario join asignatura on horario.codAsig = asignatura.codAsig where horasSemanales >= 3 group by asignatura.codAsig;";
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("%s %s %s %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
    }
}
