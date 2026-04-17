package ui;

import javax.swing.*;
import java.awt.*;
import models.Question;
import service.QuestionService;
import java.util.List;

public class ViewAllQuestions extends JFrame {

    JTextArea textArea;
    QuestionService questionService = new QuestionService();

    public ViewAllQuestions() {

        setTitle("View All Questions");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 40));

        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);

        loadData();

        btnBack.addActionListener(e -> {
//            new AdminMenu();
            dispose();
        });

        setVisible(true);
    }

    private void loadData() {

    List<Question> list = questionService.getAllQuestions();

    StringBuilder sb = new StringBuilder();

    int i = 1; 

    for (Question q : list) {

        sb.append("Question ID (DB): ").append(q.getId()).append("\n");

        sb.append("Question: ").append(q.getQuestion()).append("\n");
        sb.append("Option 1: ").append(q.getOption1()).append("\n");
        sb.append("Option 2: ").append(q.getOption2()).append("\n");
        sb.append("Option 3: ").append(q.getOption3()).append("\n");
        sb.append("Option 4: ").append(q.getOption4()).append("\n");
        sb.append("Answer: ").append(q.getAnswer()).append("\n");

        sb.append("------------------------------------------------------------\n\n");

        i++;
    }

    textArea.setText(sb.toString());
}

    public static void main(String[] args) {
        new ViewAllQuestions();
    }
}
