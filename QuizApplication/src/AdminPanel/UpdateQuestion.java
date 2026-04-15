package AdminPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateQuestion extends JFrame {

    private JTextField txtQuestionId, txtQuestion, txtOption1, txtOption2, txtOption3, txtOption4, txtAnswer;

    public UpdateQuestion() {
        setTitle("Update Question");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblId = new JLabel("Question ID :");
        txtQuestionId = new JTextField(10);
        JButton btnSearch = new JButton("Search");

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblId, gbc);

        gbc.gridx = 1;
        add(txtQuestionId, gbc);

        gbc.gridx = 2;
        add(btnSearch, gbc);

        txtQuestion = new JTextField(30);
        addField("Question :", txtQuestion, 2, gbc, true, false);

        txtOption1 = new JTextField(30);
        addField("Option 1 :", txtOption1, 3, gbc, true, false);

        txtOption2 = new JTextField(30);
        addField("Option 2 :", txtOption2, 4, gbc, true, false);

        txtOption3 = new JTextField(30);
        addField("Option 3 :", txtOption3, 5, gbc, true, false);

        txtOption4 = new JTextField(30);
        addField("Option 4 :", txtOption4, 6, gbc, true, false);

        txtAnswer = new JTextField(20);
        addField("Answer :", txtAnswer, 7, gbc, false, true);

        JButton btnUpdate = new JButton("Update Question");
        JButton btnBack = new JButton("Back");

        btnUpdate.setPreferredSize(new Dimension(180, 50));
        btnBack.setPreferredSize(new Dimension(180, 50));

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnUpdate, gbc);

        gbc.gridx = 2;
        add(btnBack, gbc);

        setVisible(true);

        btnSearch.addActionListener(e -> {
            String id = txtQuestionId.getText();

            try {
                Connection con = Database.DbConnection.getConnection();
                String query = "SELECT * FROM question WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    txtQuestion.setText(rs.getString("question"));
                    txtOption1.setText(rs.getString("option1"));
                    txtOption2.setText(rs.getString("option2"));
                    txtOption3.setText(rs.getString("option3"));
                    txtOption4.setText(rs.getString("option4"));
                    txtAnswer.setText(rs.getString("answer"));
                } else {
                    JOptionPane.showMessageDialog(null, "Question not found!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnUpdate.addActionListener(e -> {

            String id = txtQuestionId.getText();
            String question = txtQuestion.getText();
            String option1 = txtOption1.getText();
            String option2 = txtOption2.getText();
            String option3 = txtOption3.getText();
            String option4 = txtOption4.getText();
            String answer = txtAnswer.getText();

            try {
                Connection con = Database.DbConnection.getConnection();

                String query = "UPDATE question SET question=?, option1=?, option2=?, option3=?, option4=?, answer=? WHERE id=?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, question);
                ps.setString(2, option1);
                ps.setString(3, option2);
                ps.setString(4, option3);
                ps.setString(5, option4);
                ps.setString(6, answer);
                ps.setString(7, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Question Updated Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Update Failed!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            new AdminMenu();
            dispose();
        });
    }

    private void addField(String labelText, JTextField textField, int y,
                          GridBagConstraints gbc, boolean fullWidth, boolean isSmall) {

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;

        if (fullWidth) {
            gbc.gridwidth = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        } else {
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
        }

        gbc.ipady = 15;

        if (isSmall) {
            textField.setPreferredSize(new Dimension(150, 30));
            gbc.weightx = 0.3;
        }

        add(textField, gbc);
    }

    public static void main(String[] args) {
        new UpdateQuestion();
    }
}