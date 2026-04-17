package ui;

import javax.swing.*;
import java.awt.*;
import ui.QuizLogin;

public class ResultPage extends JFrame {

    public ResultPage(int score, int total, int attempted, int correct, int wrong, int unattempted) {

        setTitle("Quiz Result");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Quiz Result", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridy = 0;
        add(title, gbc);

        JLabel lblScore = new JLabel("Score: " + score + " / " + total);
        JLabel lblAttempted = new JLabel("Attempted: " + attempted);
        JLabel lblCorrect = new JLabel("Correct: " + correct);
        JLabel lblWrong = new JLabel("Wrong: " + wrong);
        JLabel lblUnattempted = new JLabel("Unattempted: " + unattempted);

        lblScore.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAttempted.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCorrect.setFont(new Font("Arial", Font.PLAIN, 16));
        lblWrong.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUnattempted.setFont(new Font("Arial", Font.PLAIN, 16));

        gbc.gridy = 1; add(lblScore, gbc);
        gbc.gridy = 2; add(lblAttempted, gbc);
        gbc.gridy = 3; add(lblCorrect, gbc);
        gbc.gridy = 4; add(lblWrong, gbc);
        gbc.gridy = 5; add(lblUnattempted, gbc);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridy = 6;
        add(btnLogout, gbc);

        btnLogout.addActionListener(e -> {
            new QuizLogin();
            dispose();
        });

        setVisible(true);
    }
}