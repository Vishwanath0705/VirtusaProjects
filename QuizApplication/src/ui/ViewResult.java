package ui;

import java.awt.*;
import javax.swing.*;
import models.Student;
import service.StudentService;
import java.util.List;

public class ViewResult extends JFrame {

    JTextArea textArea;
    StudentService studentService = new StudentService();

    public ViewResult() {

        setTitle("View Results");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 40));

        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> {
//            new AdminMenu();
            dispose();
        });

        loadData();

        setVisible(true);
    }

    private void loadData() {

        try {

            List<Student> list = studentService.getAllResults();

            StringBuilder sb = new StringBuilder();

            for (Student s : list) {

                sb.append("Student ID: ").append(s.getId()).append("\n");
                sb.append("Student Name: ").append(s.getName()).append("\n");
                sb.append("Roll Number: ").append(s.getRollNo()).append("\n");
                sb.append("Email: ").append(s.getEmail()).append("\n");
                sb.append("Phone Number: ").append(s.getPhoneNumber()).append("\n");
                sb.append("Marks: ").append(s.getMarks()).append("\n");
                sb.append("Total Questions: ").append(s.getTotalQuestions()).append("\n");
                sb.append("Percentage: ").append(s.getPercentage()).append("\n");

                sb.append("------------------------------------------------------------\n\n");
            }

            textArea.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewResult();
    }
}