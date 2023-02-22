package examen;

import java.sql.*;

public class EjercicioDos {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new EjercicioDos().app();
    }

    void app() throws SQLException, ClassNotFoundException {
        String script;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs;
        script = """
                select ofertaeducativa.codOe, nombre, count(DISTINCT curso.codCurso), count(DISTINCT reparto.codAsig), count(DISTINCT reparto.codProf)
                        from ofertaEducativa
                        left join curso on curso.codOe = ofertaEducativa.codOe
                        left join reparto on ofertaeducativa.codOe = reparto.codOe
                        group by ofertaeducativa.codOe""";

        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("""
                    Código: %s
                    Nombre: %s\s
                    Número de cursos: %s\s
                    Número de asignaturas:%s\s
                    Número de profesores: %s

                    """, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        rs.close();
        statement.close();
        connection.close();
    }
}
