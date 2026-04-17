package ui;

import javax.swing.*;
import java.awt.*;
import ui.InstructionsPage;

public class StudentDetails extends JFrame {

    JTextField txtName, txtRoll, txtEmail, txtPhoneNumber;

    public StudentDetails() {

        setTitle("Student Details");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Student Details", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);

        txtName = new JTextField(15);
        gbc.gridx = 1;
        add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Roll Number:"), gbc);

        txtRoll = new JTextField(15);
        gbc.gridx = 1;
        add(txtRoll, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);

        txtEmail = new JTextField(15);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Phone Number:"), gbc);

        txtPhoneNumber = new JTextField(15);
        gbc.gridx = 1;
        add(txtPhoneNumber, gbc);

        JButton btnProceed = new JButton("Proceed");
        btnProceed.setFont(new Font("Arial", Font.BOLD, 14));
        btnProceed.setPreferredSize(new Dimension(120, 35));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnProceed, gbc);

        btnProceed.addActionListener(e -> {

            String name = txtName.getText().trim();
            String roll = txtRoll.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhoneNumber.getText().trim();

            if (name.isEmpty() || roll.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all details!");
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