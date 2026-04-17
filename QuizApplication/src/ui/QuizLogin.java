package ui;

import java.awt.*;
import javax.swing.*;
import models.User;
import service.UserService;
import ui.AdminMenu;
import ui.ForgotPassword;
import ui.Register;
import ui.StudentDetails;

public class QuizLogin extends JFrame {

    public QuizLogin() {
        setTitle("Quiz Application - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblLogin = new JLabel("Login", SwingConstants.CENTER);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblLogin, gbc);

        JTextField txtUserName = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username : "), gbc);

        gbc.gridx = 1;
        add(txtUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password : "), gbc);

        gbc.gridx = 1;
        add(txtPassword, gbc);

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");
        JButton btnForgot = new JButton("Forgot Password");

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnLogin, gbc);

        gbc.gridx = 1;
        add(btnForgot, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnRegister, gbc);

        btnLogin.addActionListener(e -> {
            String username = txtUserName.getText();
            String password = new String(txtPassword.getPassword());

            UserService service = new UserService();
            User user = service.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();

                if (user.getUserType().equalsIgnoreCase("admin")) {
                    new AdminMenu();
                } else {
                    new StudentDetails();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        });

        btnRegister.addActionListener(e -> new Register());
        btnForgot.addActionListener(e -> new ForgotPassword());

        setVisible(true);
    }
}