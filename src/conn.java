import java.sql.*;
public class conn {
    private static final String URL = "jdbc:mysql://localhost:3306/university?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MyPass";
    Connection connection;
    Statement statement;

    conn(){
        try{
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
