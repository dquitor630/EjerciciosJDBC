package activity13;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String>[] subjects = new ArrayList[5];
        String classroom, type, script;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        Statement statement = connection.createStatement();
        for (int i = 0; i < 5; i++) {
            subjects[i] = new ArrayList<String>();
        }
        System.out.println("escribe la oferta educativa correspondiente:");
        type = keyboard.nextLine();
        System.out.println("escribe el curso");
        classroom = keyboard.nextLine();
        script = "SELECT codAsig, horaInicio, horaFin, dia FROM horario join tramoHorario where horario.codTramo = tramoHorario.codTramo and codOe = \"" + type +"\" and codCurso = \"" + classroom +"\" order by horaInicio, horario.codAsig desc;";
        rs = statement.executeQuery(script);
        while (rs.next())

            if (rs.getString("dia").equals("LUNES")) {
                if(rs.getString("codAsig").charAt(0) == '@'){
                    subjects[0].set(subjects[0].size() - 1,"*" +  subjects[0].get(subjects[0].size() - 1) );
                }else{
                    subjects[0].add(rs.getString("codAsig"));
                }
            } else if (rs.getString("dia").equals("MARTES")) {
                if(rs.getString("codAsig").charAt(0) == '@'){
                    subjects[1].set(subjects[0].size() - 1,"*" +  subjects[0].get(subjects[0].size() - 1) );
                }else{
                    subjects[1].add(rs.getString("codAsig"));
                }
            } else if (rs.getString("dia").equals("MIERCOLES")) {
                if(rs.getString("codAsig").charAt(0) == '@'){
                    subjects[2].set(subjects[0].size() - 1,"*" +  subjects[0].get(subjects[0].size() - 1) );
                }else{
                    subjects[2].add(rs.getString("codAsig"));
                }
            } else if (rs.getString("dia").equals("JUEVES")) {
                if(rs.getString("codAsig").charAt(0) == '@'){
                    subjects[3].set(subjects[0].size() - 1,"*" +  subjects[0].get(subjects[0].size() - 1) );
                }else{
                    subjects[3].add(rs.getString("codAsig"));
                }
            } else if (rs.getString("dia").equals("VIERNES")) {
                if(rs.getString("codAsig").charAt(0) == '@'){
                    subjects[4].set(subjects[0].size() - 1,"*" +  subjects[0].get(subjects[0].size() - 1) );
                }else{
                    subjects[4].add(rs.getString("codAsig"));
                }
            }
        System.out.printf("%7s %7s %7s %7s %7s \n", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < subjects.length; j++){
                System.out.printf("%7s ", subjects[j].get(i));
            }
            System.out.println();
        }
    }

}

