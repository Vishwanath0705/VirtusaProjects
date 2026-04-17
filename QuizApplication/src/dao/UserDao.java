
package dao;
import models.User;
import java.sql.*;
import database.DbConnection;

public class UserDao {
    // register
    public boolean registerUser(User user) throws SQLException{
        String sql = "INSERT INTO users (username, email, mobile, password, user_type) VALUES (?,?,?,?,?)";
        try(Connection conn = DbConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getMobile());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getUserType());

            return pst.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }   
    
    // login
    public User loginUser(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // 🔥 3. Update Password (Forgot Password)
    public boolean updatePassword(String username, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE username=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔥 4. Check Username Exists (Validation)
    public boolean isUserExists(String username) {

        String sql = "SELECT id FROM users WHERE username=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
