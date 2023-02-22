package examen;

import java.sql.*;

public class EjercicioUno {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new EjercicioUno().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        String script;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs;
        script = """
                select profesor.nombre, apellidos, IFNULL(ofertaeducativa.nombre, 'Este profesor no es tutor'), IfNull(curso.codOe, 'este profesor no es tutor'), count(codAsig), IfNull(curso.codCurso, '')
                        from profesor
                        left join curso on profesor.codProf = codTutor
                        left join ofertaeducativa on curso.codOe = ofertaeducativa.codOe
                        left join reparto on profesor.codProf = reparto.codProf
                        group by profesor.codProf""";

        rs = statement.executeQuery(script);
        while (rs.next()) {
            System.out.printf("""
                    Nombre: %s
                    Apellidos: %s\s
                    Nombre oferta: %s\s
                    Código oferta:%s %s\s
                    Número asignaturas: %s

                    """, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(5));
        }
        rs.close();
        statement.close();
        connection.close();
    }
}
