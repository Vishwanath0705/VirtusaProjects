
package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register extends JFrame{
    public Register(){
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
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

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; add(txtUserName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Mobile:"), gbc);
        gbc.gridx = 1; add(txtMobile, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(txtPassword, gbc);

        JRadioButton btnAdmin = new JRadioButton("Admin");
        JRadioButton btnUser = new JRadioButton("User");

        ButtonGroup group = new ButtonGroup();
        group.add(btnAdmin);
        group.add(btnUser);

        gbc.gridx = 0; gbc.gridy = 5; add(btnAdmin, gbc);
        gbc.gridx = 1; add(btnUser, gbc);

        JButton btnRegister = new JButton("Register");
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnRegister, gbc);

        btnRegister.addActionListener(e -> {
            String username = txtUserName.getText().trim();
            String email = txtEmail.getText().trim();
            String mobile = txtMobile.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill all required fields!");
                return;
            }

            if(!btnAdmin.isSelected() && !btnUser.isSelected()){
                JOptionPane.showMessageDialog(null, "Select user type!");
                return;
            }

            String userType = btnAdmin.isSelected() ? "admin" : "user";

            try{
                Connection conn = Database.DbConnection.getConnection();

                String query = "INSERT INTO users (username, email, mobile, password, user_type) VALUES (?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, email);
                pst.setString(3, mobile);
                pst.setString(4, password);
                pst.setString(5, userType);

                int rows = pst.executeUpdate();

                if(rows > 0){
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    dispose();
                    new QuizLogin();
                }

                conn.close();

            } catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}