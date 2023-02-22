package examen;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class EjercicioTres {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new EjercicioTres().app();
    }

    void app() throws SQLException, ClassNotFoundException {
        //definitivamente utilizar un mapa, clave código de la asignatura
        //ir guardando si encuentra coincidencias en cada tramo, cada campo valor del mapa
        //tendrá una array de dos dimensiones de 5 posiciones(una por dia) y la segunda de 6
        //variable current para saber la asignatura actual

        System.out.println("""
                *************************************************************************
                                       Asignaturas Del Ies Saladillo                     
                *************************************************************************""");
        Map<String, String[][]> subjects = new LinkedHashMap<>();
        String[][] reference;
        String script;
        String hour;
        StringBuilder hours = new StringBuilder();
        String newAsig = "";
        String codOe = "";
        String current = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs;
        script = """
                SELECT asignatura.codAsig, asignatura.nombre, horasSemanales, horario.codOe, horario.codCurso,  tramoHorario.codTramo
                FROM asignatura
                left join horario on asignatura.codAsig = horario.codAsig
                left join tramoHorario on horario.codTramo = tramohorario.codTramo
                order by horario.codAsig, codOe;""";

        rs = statement.executeQuery(script);
        while (rs.next()) {
            if (current == null) {
                //esto dará problemas en asignaturas como fol o eie, ya arreglaremos el tema
                current = rs.getString(1);
                codOe = rs.getString(4);
                newAsig = current;

                System.out.printf("Código: %s Nombre %s Horas Semanales: %s \n Código curso: %s %s ",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                subjects.put(rs.getString(1), new String[5][6]);


            } else if (!current.equals(rs.getString(1))) {

                reference = subjects.get(current);

                System.out.print("Tramos horarios: ");
                for (String[] strings : reference) {
                    for (String string : strings) {
                        if (!(string == null || string.equals(""))) {
                            hours.append(string).append(",");
                        }
                    }
                }
                System.out.print(hours.substring(0, hours.length() - 1));
                hours = new StringBuilder();

                if (!current.equals(newAsig)) {
                    reference = subjects.get(newAsig);
                    System.out.printf("\n Código curso: %s Tramos horarios:", newAsig);
                    for (String[] strings : reference) {
                        for (String string : strings) {
                            if (!(string == null || string.equals(""))) {
                                hours.append(string).append(",");
                            }
                        }
                    }
                    System.out.print(hours.substring(0, hours.length() - 1));
                    hours = new StringBuilder();
                }

                System.out.println();

                current = rs.getString(1);
                codOe = rs.getString(4);
                newAsig = current;
                System.out.printf("Código: %s Nombre %s Horas Semanales: %s \n Código curso: %s %s ",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                subjects.put(rs.getString(1), new String[5][6]);
            }

            if (!codOe.equals(rs.getString(4))) {
                codOe = rs.getString(4);
                newAsig = codOe + " " + rs.getString(5);
                subjects.put(newAsig, new String[5][6]);
            }

            hour = rs.getString(6);

            if (hour.charAt(0) == 'L') {
                reference = subjects.get(newAsig);
                reference[0][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
            } else if (hour.charAt(0) == 'M') {
                reference = subjects.get(newAsig);
                reference[1][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
            } else if (hour.charAt(0) == 'X') {
                reference = subjects.get(newAsig);
                reference[2][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
            } else if (hour.charAt(0) == 'J') {
                reference = subjects.get(newAsig);
                reference[3][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
            } else if (hour.charAt(0) == 'V') {
                reference = subjects.get(newAsig);
                reference[4][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
            }
        }
        rs.close();
        statement.close();
        connection.close();
    }
}
