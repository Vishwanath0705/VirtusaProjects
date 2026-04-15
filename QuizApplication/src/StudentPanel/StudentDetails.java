package StudentPanel;
import javax.swing.*;
import java.awt.*;

public class StudentDetails extends JFrame {
    JTextField txtName, txtRoll, txtEmail, txtPhoneNumber;

    public StudentDetails() {
        setTitle("Student Details");
        setSize(400, 300);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        txtName = new JTextField(15);
        gbc.gridx = 1;
        add(txtName, gbc);

        // Roll Number
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Roll Number:"), gbc);

        txtRoll = new JTextField(15);
        gbc.gridx = 1;
        add(txtRoll, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);

        txtEmail = new JTextField(15);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Phone Number:"), gbc);

        txtPhoneNumber = new JTextField(15);
        gbc.gridx = 1;
        add(txtPhoneNumber, gbc);

        // Button
        JButton btnProceed = new JButton("Proceed");
        btnProceed.setPreferredSize(new Dimension(120, 35));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnProceed, gbc);

        // ✅ Action (CONNECTED TO INSTRUCTIONS PAGE)
        btnProceed.addActionListener(e -> {
            String name = txtName.getText();
            String roll = txtRoll.getText();
            String email = txtEmail.getText();
            String phone = txtPhoneNumber.getText();

            if(name.isEmpty() || roll.isEmpty() || email.isEmpty() || phone.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all details!");
                return;
            }

            new InstructionsPage(name, roll, email, phone);
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}