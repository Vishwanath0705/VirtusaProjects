package ui;

import javax.swing.*;
import java.awt.*;
import models.Question;
import service.QuestionService;
import ui.AdminMenu;

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
        gbc.gridy = 0;
        add(lblId, gbc);

        gbc.gridx = 1;
        add(txtQuestionId, gbc);

        gbc.gridx = 2;
        add(btnSearch, gbc);

        txtQuestion = new JTextField(30);
        txtOption1 = new JTextField(30);
        txtOption2 = new JTextField(30);
        txtOption3 = new JTextField(30);
        txtOption4 = new JTextField(30);
        txtAnswer = new JTextField(20);

        addField("Question :", txtQuestion, 1, gbc, true);
        addField("Option 1 :", txtOption1, 2, gbc, true);
        addField("Option 2 :", txtOption2, 3, gbc, true);
        addField("Option 3 :", txtOption3, 4, gbc, true);
        addField("Option 4 :", txtOption4, 5, gbc, true);
        addField("Answer :", txtAnswer, 6, gbc, false);

        JButton btnUpdate = new JButton("Update Question");
        JButton btnBack = new JButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(btnUpdate, gbc);

        gbc.gridx = 2;
        add(btnBack, gbc);

        setVisible(true);

        btnSearch.addActionListener(e -> searchQuestion());
        btnUpdate.addActionListener(e -> updateQuestion());
        btnBack.addActionListener(e -> {
//            new AdminMenu();
            dispose();
        });
    }

    private void searchQuestion() {

        try {
            int id = Integer.parseInt(txtQuestionId.getText());

            QuestionService service = new QuestionService();
            Question q = service.getQuestionById(id);

            if (q != null) {
                txtQuestion.setText(q.getQuestion());
                txtOption1.setText(q.getOption1());
                txtOption2.setText(q.getOption2());
                txtOption3.setText(q.getOption3());
                txtOption4.setText(q.getOption4());
                txtAnswer.setText(q.getAnswer());
            } else {
                JOptionPane.showMessageDialog(this, "Question not found!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void updateQuestion() {

        try {
            Question q = new Question();

            q.setId(Integer.parseInt(txtQuestionId.getText()));
            q.setQuestion(txtQuestion.getText());
            q.setOption1(txtOption1.getText());
            q.setOption2(txtOption2.getText());
            q.setOption3(txtOption3.getText());
            q.setOption4(txtOption4.getText());
            q.setAnswer(txtAnswer.getText());

            QuestionService service = new QuestionService();
            boolean updated = service.updateQuestion(q);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Question Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void addField(String label, JTextField field, int y, GridBagConstraints gbc, boolean full) {

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;

        if (full) {
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
        } else {
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
        }

        add(field, gbc);
    }

    public static void main(String[] args) {
        new UpdateQuestion();
    }
}