package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUrl = "jdbc:mysql://localhost:3306/quiz_application?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        String user = "quiz_user";
        String password = "Vishwanath@26";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS quiz_application");
            System.out.println("Database created successfully");

            stmt.close();
            conn.close();

            conn = DriverManager.getConnection(dbUrl, user, password);
            stmt = conn.createStatement();

            // USERS TABLE
            String createUsersTable =
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "mobile VARCHAR(15), " +
                    "password VARCHAR(100) NOT NULL, " +
                    "user_type VARCHAR(10) NOT NULL" +
                    ") ENGINE=InnoDB";

            stmt.executeUpdate(createUsersTable);
            System.out.println("Users table ready");

            // QUESTION TABLE
            String createQuestionTable =
                    "CREATE TABLE IF NOT EXISTS question (" +
                    "id INT PRIMARY KEY, " +
                    "question VARCHAR(255) UNIQUE NOT NULL, " +
                    "option1 VARCHAR(255) NOT NULL, " +
                    "option2 VARCHAR(255) NOT NULL, " +
                    "option3 VARCHAR(255) NOT NULL, " +
                    "option4 VARCHAR(255) NOT NULL, " +
                    "answer VARCHAR(255) NOT NULL" +
                    ") ENGINE=InnoDB";

            stmt.executeUpdate(createQuestionTable);
            System.out.println("Question table ready");

            // STUDENT TABLE
            String createStudentsTable =
                    "CREATE TABLE IF NOT EXISTS student (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "roll_no VARCHAR(50) UNIQUE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "phone_number VARCHAR(15) UNIQUE NOT NULL, " +
                    "marks INT, " +
                    "total_questions INT, " +
                    "percentage DOUBLE" +
                    ") ENGINE=InnoDB";

            stmt.executeUpdate(createStudentsTable);
            System.out.println("Students table ready");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}