package ui;

import javax.swing.*;
import java.awt.*;
import service.QuestionService;

public class DeleteQuestion extends JFrame {

    private JTextField txtQuestionId;

    public DeleteQuestion() {
        setTitle("Delete Question");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblId = new JLabel("Question ID:");
        txtQuestionId = new JTextField(15);
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblId, gbc);

        gbc.gridx = 1;
        add(txtQuestionId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnDelete, gbc);

        gbc.gridx = 1;
        add(btnBack, gbc);

        setVisible(true);

        btnDelete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this question?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                QuestionService service = new QuestionService();

                boolean deleted = service.deleteQuestion(
                        Integer.parseInt(txtQuestionId.getText())
                );

                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Question Deleted Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Question ID not found!");
                }

                txtQuestionId.setText("");
            }
        });

        btnBack.addActionListener(e -> {
//            new AdminMenu();
            dispose();
        });
    }

    
}