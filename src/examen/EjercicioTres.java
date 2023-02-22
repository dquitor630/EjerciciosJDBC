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

        /*SELECT * FROM asignatura
            left join horario on asignatura.codAsig = horario.codAsig
            left join tramoHorario on horario.codTramo = tramohorario.codTramo
            order by horario.codAsig, horaInicio;*/
        Map<String, String[][]> subjects = new LinkedHashMap<>();
        String[][] reference;
        String script, hour, current = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs;
        script = """
                SELECT asignatura.codAsig, asignatura.nombre, horasSemanales, horario.codOe, horario.codCurso,  tramoHorario.codTramo
                FROM asignatura
                left join horario on asignatura.codAsig = horario.codAsig
                left join tramoHorario on horario.codTramo = tramohorario.codTramo
                order by horario.codAsig, horaInicio;""";

        rs = statement.executeQuery(script);
        while (rs.next()) {
           if(current == null){
               //esto dará problemas en asignaturas como fol o eie, ya arreglaremos el tema
                current = rs.getString(1);

                System.out.printf("Código: %s Nombre %s Horas Semanales: %s \n Código curso: %s %s ",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                subjects.put(rs.getString(1), new String[5][6]);
            }else if(!current.equals(rs.getString(1))){
               reference = subjects.get(current);

               System.out.print("Tramos horarios: ");
               for (String[] strings : reference) {
                   for (String string : strings) {
                       if (!(string == null || string.equals(""))) {
                           System.out.print(string + " ");
                       }
                   }
               }
               System.out.println();

               current = rs.getString(1);

               System.out.printf("Código: %s Nombre %s Horas Semanales: %s \n Código curso: %s %s ",
                       rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

               subjects.put(rs.getString(1), new String[5][6]);
           }else{
               hour = rs.getString(6);
                if(hour.charAt(0) == 'L'){
                    reference = subjects.get(current);
                    reference[0][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
                }else if(hour.charAt(0) == 'M'){
                    reference = subjects.get(current);
                    reference[1][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
                }else if(hour.charAt(0) == 'X'){
                    reference = subjects.get(current);
                    reference[2][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
                }else if(hour.charAt(0) == 'J'){
                    reference = subjects.get(current);
                    reference[3][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
                }else if(hour.charAt(0) == 'V'){
                    reference = subjects.get(current);
                    reference[4][Integer.parseInt(hour.substring(1, 2)) - 1] = hour;
                }
           }
        }
        rs.close();
        statement.close();
        connection.close();
    }
}
