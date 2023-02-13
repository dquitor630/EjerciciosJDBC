package activity10;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        String script = "SELECT codCurso, ofertaeducativa.nombre, profesor.nombre FROM curso Left Join ofertaeducativa on curso.codOe = ofertaeducativa.codOe Left Join profesor on codTutor = codProf;";
        Statement statement = connection.createStatement();
        boolean value = statement.execute(script);
        if (value) {
            ResultSet rs = statement.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){
                System.out.printf("%s %s %s\n", rs.getString(1),rs.getString(2), rs.getString(3));
            }
        }
        statement.close();
        connection.close();
    }
}
