package ui;

import javax.swing.*;
import java.awt.*;
import service.UserService;

public class ForgotPassword extends JFrame {

    public ForgotPassword() {
        setTitle("Forgot Password");
        setSize(400, 300);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField txtUser = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);

        gbc.gridx=0; gbc.gridy=0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx=1;
        add(txtUser, gbc);

        gbc.gridx=0; gbc.gridy=1;
        add(new JLabel("Email:"), gbc);
        gbc.gridx=1;
        add(txtEmail, gbc);

        gbc.gridx=0; gbc.gridy=2;
        add(new JLabel("New Password:"), gbc);
        gbc.gridx=1;
        add(txtPass, gbc);

        JButton btn = new JButton("Update");

        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2;
        add(btn, gbc);

        btn.addActionListener(e -> {
            UserService service = new UserService();

            boolean updated = service.resetPassword(
                    txtUser.getText(),
                    new String(txtPass.getPassword())
            );

            if (updated) {
                JOptionPane.showMessageDialog(this, "Password Updated");
                dispose();
                new QuizLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Details");
            }
        });

        setVisible(true);
    }
}