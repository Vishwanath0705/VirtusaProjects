package ui;

import javax.swing.*;
import java.awt.*;
import models.User;
import service.UserService;
import ui.QuizLogin;

public class Register extends JFrame {

    public Register() {

        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblRegister = new JLabel("Register", SwingConstants.CENTER);
        lblRegister.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblRegister, gbc);

        JTextField txtUserName = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JTextField txtMobile = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(txtUserName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Mobile:"), gbc);
        gbc.gridx = 1;
        add(txtMobile, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        JRadioButton btnAdmin = new JRadioButton("Admin");
        JRadioButton btnUser = new JRadioButton("User");

        ButtonGroup group = new ButtonGroup();
        group.add(btnAdmin);
        group.add(btnUser);

        gbc.gridx = 0; gbc.gridy = 5;
        add(btnAdmin, gbc);
        gbc.gridx = 1;
        add(btnUser, gbc);

        JButton btnRegister = new JButton("Register");

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnRegister, gbc);

        btnRegister.addActionListener(e -> {

            String username = txtUserName.getText().trim();
            String email = txtEmail.getText().trim();
            String mobile = txtMobile.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all required fields!");
                return;
            }

            if (!btnAdmin.isSelected() && !btnUser.isSelected()) {
                JOptionPane.showMessageDialog(this, "Select user type!");
                return;
            }

            String userType = btnAdmin.isSelected() ? "admin" : "user";

            try {

                User user = new User();
                user.setUserName(username);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setPassword(password);
                user.setUserType(userType);

                UserService service = new UserService();

                boolean success = service.register(user);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Registration Successful!");
                    dispose();
                    new QuizLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "Registration Failed!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}