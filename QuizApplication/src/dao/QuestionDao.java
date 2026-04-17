
package dao;
import models.Question;
import database.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    
    // add question
    public boolean addQuestion(Question q) throws SQLException {

        String sql = "INSERT INTO question (id, question, option1, option2, option3, option4, answer) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, q.getId());
            pst.setString(2, q.getQuestion());
            pst.setString(3, q.getOption1());
            pst.setString(4, q.getOption2());
            pst.setString(5, q.getOption3());
            pst.setString(6, q.getOption4());
            pst.setString(7, q.getAnswer());

            return pst.executeUpdate() > 0;
        }catch(Exception e){
                e.printStackTrace();
                return false;
            }
    }
    
    public boolean isIdExists(int id) {
        String sql = "SELECT id FROM question WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // view all questions
    public List<Question> getAllQuestions(){
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM question ORDER BY id ASC";
        
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Question q = new Question();

                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setAnswer(rs.getString("answer"));

                list.add(q);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    // update question
    public boolean updateQuestion(Question q){
        String sql = "UPDATE question SET question=?, option1=?, option2=?, option3=?, option4=?, answer=? WHERE id=?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, q.getQuestion());
            ps.setString(2, q.getOption1());
            ps.setString(3, q.getOption2());
            ps.setString(4, q.getOption3());
            ps.setString(5, q.getOption4());
            ps.setString(6, q.getAnswer());
            ps.setInt(7, q.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // delete question
    public boolean deleteQuestion(int id){
        String sql = "DELETE FROM question where id=?";
        
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getQuestionCount() throws SQLException {
    Connection con = DbConnection.getConnection();
    String sql = "SELECT COUNT(*) FROM question";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) return rs.getInt(1);
    return 0;
}

    public Question getQuestionById(int id) {
        Question q = null;

        try {
            Connection con = database.DbConnection.getConnection();

            String sql = "SELECT * FROM question WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setAnswer(rs.getString("answer"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return q;
    }
    
}
