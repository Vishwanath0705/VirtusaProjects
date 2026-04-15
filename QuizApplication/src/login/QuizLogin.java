
package login;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.DatabaseSetup;
import AdminPanel.AdminMenu;
import StudentPanel.StudentDetails;

public class QuizLogin extends JFrame{
    public QuizLogin(){
        setTitle("Quiz Application - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblLogin = new JLabel("Login", SwingConstants.CENTER);
        lblLogin.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        add(lblLogin, gbc);
        
        JLabel userName = new JLabel("Username : " );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(userName, gbc);
        
        JTextField txtUserName = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUserName, gbc);
        
        JLabel password = new JLabel("Password : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(password, gbc);
        
        JPasswordField txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtPassword, gbc);
        
        JButton btnLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnLogin, gbc);
        
        JButton btnForgotPassword = new JButton("Forgot Password");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnForgotPassword, gbc);
        
        JButton btnRegister = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnRegister, gbc);
        
        btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String userName = txtUserName.getText();
                String password = new String(txtPassword.getPassword()).trim();
                
                if(userName.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter username and password!!");
                    return;
                }
                
                try{
                    Connection conn = Database.DbConnection.getConnection();
                    
                    String query = "SELECT * FROM users WHERE username=? AND password=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    
                    pst.setString(1, userName);
                    pst.setString(2, password);
                    
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                    String userType = rs.getString("user_type");

                    JOptionPane.showMessageDialog(null, "Login Successful");
                    dispose();

                    if(userType.equalsIgnoreCase("admin")){
                        JOptionPane.showMessageDialog(null, "Welcome Admin!");
                        new AdminMenu();   // open admin panel
                    } else {
                        JOptionPane.showMessageDialog(null, "Welcome User!");
                        new StudentDetails();   // open student panel
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
                    conn.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error : "+ex.getMessage());
                }
//                new AdminMenu();
            }
        });
        
        btnRegister.addActionListener(e -> new Register());
        btnForgotPassword.addActionListener(e -> new ForgotPassword());
        
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        DatabaseSetup.main(null);
        new QuizLogin();
    }
}
