
package login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ForgotPassword extends JFrame{
    public ForgotPassword(){
        setTitle("Forgot Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblUserName = new JLabel("Username : ");
        JTextField txtUserName = new JTextField(15);
        JLabel lblEmail = new JLabel("Email : ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblNewPassword = new JLabel("Passoword : ");
        JPasswordField txtNewPassword = new JPasswordField(15);
        JButton btnUpdate = new JButton("Update");
        
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblUserName, gbc);
        gbc.gridx = 1;gbc.gridy = 1;
        add(txtUserName, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblEmail, gbc);
        gbc.gridx = 1;gbc.gridy = 2;
        add(txtEmail, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblNewPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        add(txtNewPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnUpdate, gbc);
        
        btnUpdate.addActionListener(e -> {
            try{
                Connection conn = Database.DbConnection.getConnection();
                String query = "UPDATE users SET password=? WHERE username=?";
                
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, new String(txtNewPassword.getPassword()));
                pst.setString(2, txtUserName.getText());

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Password Updated Successfully!");
                    dispose();
                    new QuizLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "User Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        setVisible(true);
        
    }
}
