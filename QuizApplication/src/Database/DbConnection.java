
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_app";
    private static final String USER = "quiz_user";
    private static final String PASSWORD = "Vishwanath@26";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
