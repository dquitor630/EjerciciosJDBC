package activity17;

import java.sql.*;
import java.util.Scanner;

/*DELIMITER //
CREATE FUNCTION getTeacher(courseId varchar(255), offerId varchar(255))
RETURNS VARCHAR(255)
	BEGIN
			DECLARE nameProf VARCHAR(255);
			SELECT nombre INTO nameProf
			FROM curso left join profesor
			on codTutor = codProf
			WHERE codCurso = courseId and codOe = offerId;
			RETURN nameProf;
	END
*/
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Main().app();
    }

    void app() throws ClassNotFoundException, SQLException {
        String type, teacher, course;
        Scanner keyboard = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        CallableStatement statement = connection.prepareCall("{? = call getTeacher(?,?)}");
        statement.registerOutParameter(1, Types.VARCHAR);
        System.out.println("Introduce la oferta educativa");
        type = keyboard.nextLine();
        System.out.println("Escribe el código del curso");
        course = keyboard.nextLine();
        statement.setString(2, course);
        statement.setString(3, type);
        statement.execute();
        teacher = statement.getString(1);
        if(teacher != null){
            System.out.println(teacher);
        }
    }
}
