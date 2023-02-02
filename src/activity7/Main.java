package activity7;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Main().app();
    }

    public void app() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        String script = "SELECT * FROM profesor order by apellidos asc, fechaAlta desc;";
        Statement statement = connection.createStatement();
        boolean value = statement.execute(script);
        if (value) {
            ResultSet rs = statement.getResultSet();
            while (rs.next()){
                System.out.printf("%s %s %s %s \n", rs.getString(1),rs.getString(2), rs.getString(3), rs.getDate(4).toString() );
            }
            rs.close();
        }
        statement.close();
        connection.close();
    }
}
