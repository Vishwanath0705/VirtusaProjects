

import java.sql.Connection;
import java.sql.DriverManager;

public class test {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/quiz_application";
        String user = "quiz_user";
        String password = "Vishwanath@26";

        try {
            // load driver (safe for older setups)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("✅ DB CONNECTION SUCCESSFUL");
            } else {
                System.out.println("❌ CONNECTION FAILED");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("❌ CONNECTION ERROR");
            e.printStackTrace();
        }
    }
}