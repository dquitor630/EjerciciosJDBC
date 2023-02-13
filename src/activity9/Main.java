package activity9;

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
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){
                System.out.printf("%s %s %s %s %s \n", rs.getString(1),rs.getString(2), rs.getString(3), rs.getDate(4).toString(), rs.getString(5));
            }
            for(int i = 1; i < rsmd.getColumnCount(); i++){
                System.out.println("columna: " + rsmd.getColumnName(i));
                System.out.println("nullable: " + (rsmd.isNullable(i) == ResultSetMetaData.columnNoNulls ? "no" : "si"));
                System.out.println("tipo: " + rsmd.getColumnTypeName(i));
                System.out.println("ancho: " + rsmd.getColumnDisplaySize(i));
            }
        }
        statement.close();
        connection.close();
    }
}