package ui;

import javax.swing.*;
import java.awt.*;
import ui.QuizLogin;

public class AdminMenu extends JFrame {

    public AdminMenu() {
        setTitle("Admin Panel");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 3;

        JButton btnAddQuestion = createButton("Add Question");
        JButton btnUpdateQuestion = createButton("Update Question");
        JButton btnViewAllQuestions = createButton("View All Questions");
        JButton btnDeleteQuestion = createButton("Delete Question");
        JButton btnViewResult = createButton("View Results");
        JButton btnLogout = createButton("Logout");

        gbc.gridy = 1;
        add(btnAddQuestion, gbc);

        gbc.gridy = 2;
        add(btnUpdateQuestion, gbc);

        gbc.gridy = 3;
        add(btnViewAllQuestions, gbc);

        gbc.gridy = 4;
        add(btnDeleteQuestion, gbc);

        gbc.gridy = 5;
        add(btnViewResult, gbc);

        gbc.gridy = 6;
        add(btnLogout, gbc);

        btnAddQuestion.addActionListener(e -> new AddQuestion());
        btnUpdateQuestion.addActionListener(e -> new UpdateQuestion());
        btnViewAllQuestions.addActionListener(e -> new ViewAllQuestions());
        btnDeleteQuestion.addActionListener(e -> new DeleteQuestion());
        btnViewResult.addActionListener(e -> new ViewResult());

        btnLogout.addActionListener(e -> {
            new QuizLogin();
            dispose();
        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension size = new Dimension(250, 45);
        btn.setPreferredSize(size);
        btn.setMinimumSize(size);
        btn.setMaximumSize(size);

        btn.setFocusPainted(false);

        return btn;
    }

    
}