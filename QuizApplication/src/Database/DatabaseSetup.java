package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "quiz_user";
        String password = "Vishwanath@26";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS quiz_app");
            System.out.println("Database created successfully");

            conn.close();

            conn = DriverManager.getConnection(url + "quiz_app", user, password);
            stmt = conn.createStatement();

            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY, "+
                    "username VARCHAR(50) UNIQUE NOT NULL, "+
                    "email VARCHAR(100) UNIQUE NOT NULL, "+
                    "mobile VARCHAR(15), "+
                    "password VARCHAR(100) NOT NULL, "+
                    "user_type VARCHAR(15) NOT NULL)";
            
            stmt.executeUpdate(createUsersTable);
            System.out.println("Users table ready");
            
            String createQuestionTable = "CREATE TABLE IF NOT EXISTS question ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY, "+
                    "question VARCHAR(250) UNIQUE NOT NULL, "+
                    "option1 VARCHAR(250)  NOT NULL, "+
                    "option2 VARCHAR(250)  NOT NULL, "+
                    "option3 VARCHAR(250)  NOT NULL, "+
                    "option4 VARCHAR(250)  NOT NULL, "+
                    "answer VARCHAR(250) NOT NULL)";
            
            stmt.executeUpdate(createQuestionTable);
            System.out.println("Question table ready");
            
//            String alterQuestion = "ALTER TABLE question " +
//                "MODIFY question VARCHAR(250) NOT NULL, " +
//                "MODIFY option1 VARCHAR(250) NOT NULL, " +
//                "MODIFY option2 VARCHAR(250) NOT NULL, " +
//                "MODIFY option3 VARCHAR(250) NOT NULL, " +
//                "MODIFY option4 VARCHAR(250) NOT NULL";
//        
//stmt.executeUpdate(alterQuestion);
//System.out.println("Question table modified successfully");
            
            String createStudentsTable = "CREATE TABLE IF NOT EXISTS student (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "roll_no VARCHAR(50) UNIQUE NOT NULL, " +
                "email VARCHAR(100) UNIQUE NOT NULL, " +
                "phone_number VARCHAR(15) UNIQUE NOT NULL, " +
                "marks INT, " +
                "total_questions INT, " +
                "percentage DOUBLE" +
                ")";
            
            stmt.executeUpdate(createStudentsTable);
            System.out.println("Students table ready");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}