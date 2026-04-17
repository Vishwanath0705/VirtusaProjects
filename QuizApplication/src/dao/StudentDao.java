
package dao;
import models.Student;
import java.sql.*;
import database.DbConnection;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {
    // save student results
    public boolean saveStudentResults(Student s) throws SQLException{
        String sql = "INSERT INTO student (name, roll_no, email, phone_number, marks, total_questions, percentage) VALUES (?,?,?,?,?,?,?)";
        
        try(Connection conn = DbConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, s.getName());
            pst.setString(2, s.getRollNo());
            pst.setString(3, s.getEmail());
            pst.setString(4, s.getPhoneNumber());
            pst.setInt(5, s.getMarks());
            pst.setInt(6, s.getTotalQuestions());
            pst.setDouble(7, s.getPercentage());

            return pst.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    // get all students results
    public List<Student> getAllResult() throws SQLException{
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * from student";
        
        try(Connection conn = DbConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                Student s = new Student();

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setEmail(rs.getString("email"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setMarks(rs.getInt("marks"));
                s.setTotalQuestions(rs.getInt("total_questions"));
                s.setPercentage(rs.getDouble("percentage"));

                list.add(s);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean isExamAlreadyTaken(String rollNo) {

        String sql = "SELECT id FROM student WHERE roll_no = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rollNo);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // true = already exists

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
