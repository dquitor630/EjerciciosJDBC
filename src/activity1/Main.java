package activity1;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Main().app();
    }

    public void app() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/horario?allowMultiQueries=true", "root", "root");
        File statementScript = new File("script.sql");
        StringBuilder script = new StringBuilder();
        Statement statement = connection.createStatement();
        BufferedReader reader = new BufferedReader(new FileReader(statementScript));
        String line;
        while((line = reader.readLine()) != null) {
            script.append(line);
        }

        statement.executeUpdate(script.toString());
        connection.close();
    }
}