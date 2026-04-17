package service;

import dao.UserDao;
import java.sql.SQLException;
import models.User;

public class UserService {

    private UserDao dao = new UserDao();

    // 🔥 Register
    public boolean register(User user) throws SQLException {

        if (user == null ||
            isEmpty(user.getUsername()) ||
            isEmpty(user.getPassword())) {

            throw new RuntimeException("Username and Password required");
        }

        if (dao.isUserExists(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        return dao.registerUser(user);
    }

    // 🔥 Login
    public User login(String username, String password) {

        if (isEmpty(username) || isEmpty(password)) {
            throw new RuntimeException("Username and Password required");
        }

        return dao.loginUser(username, password);
    }

    // 🔥 Forgot Password
    public boolean resetPassword(String username, String newPassword) {

        if (isEmpty(username) || isEmpty(newPassword)) {
            throw new RuntimeException("Fields cannot be empty");
        }

        return dao.updatePassword(username, newPassword);
    }

    
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}