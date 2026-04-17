package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import models.Question;
import service.QuestionService;

public class AddQuestion extends JFrame {

    private JTextField txtQuestionId, txtOption1, txtOption2, txtOption3, txtOption4, txtAnswer;
    private JTextArea txtQuestion;

    public AddQuestion() {
        setTitle("Add Question");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        txtQuestionId = new JTextField(10);

        txtQuestion = new JTextArea(3, 30);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);

        txtOption1 = new JTextField(30);
        txtOption2 = new JTextField(30);
        txtOption3 = new JTextField(30);
        txtOption4 = new JTextField(30);
        txtAnswer = new JTextField(10);

        addRow("Question ID :", txtQuestionId, 0, gbc, false);
        addTextAreaRow("Question :", txtQuestion, 1, gbc);
        addRow("Option 1 :", txtOption1, 2, gbc, true);
        addRow("Option 2 :", txtOption2, 3, gbc, true);
        addRow("Option 3 :", txtOption3, 4, gbc, true);
        addRow("Option 4 :", txtOption4, 5, gbc, true);
        addRow("Answer :", txtAnswer, 6, gbc, false);

        JButton btnAdd = new JButton("Add Question");
        JButton btnBack = new JButton("Back");

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        gbc.gridx = 2;
        add(btnBack, gbc);

        setVisible(true);

        btnAdd.addActionListener(e -> insertQuestion());
        btnBack.addActionListener(e -> {
//            new AdminMenu();
            dispose();
        });
    }

    private void addRow(String labelText, JTextField field, int y, GridBagConstraints gbc, boolean fullWidth) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 30));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = y;

        if (fullWidth) {
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
        } else {
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
        }

        field.setPreferredSize(new Dimension(400, 35));
        add(field, gbc);
    }

    private void addTextAreaRow(String labelText, JTextArea area, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 30));

        gbc.gridx = 0;
        gbc.gridy = y;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 80));

        add(scroll, gbc);
    }

    private void insertQuestion() {
        try {
            Question q = new Question();

            q.setId(Integer.parseInt(txtQuestionId.getText())); // IMPORTANT FIX

            q.setQuestion(txtQuestion.getText());
            q.setOption1(txtOption1.getText());
            q.setOption2(txtOption2.getText());
            q.setOption3(txtOption3.getText());
            q.setOption4(txtOption4.getText());
            q.setAnswer(txtAnswer.getText());

            QuestionService service = new QuestionService();

            if (service.addQuestion(q)) {
                JOptionPane.showMessageDialog(this, "Question Added!");

                txtQuestionId.setText("");
                txtQuestion.setText("");
                txtOption1.setText("");
                txtOption2.setText("");
                txtOption3.setText("");
                txtOption4.setText("");
                txtAnswer.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid numeric ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    
}