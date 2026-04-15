package StudentPanel;

import javax.swing.*;
import java.awt.*;
import login.QuizLogin;

public class ResultPage extends JFrame {

    public ResultPage(int score, int total, int attempted, int correct, int wrong, int unattempted) {
        setTitle("Result");
        setSize(400, 400);
        setLayout(new GridLayout(6, 1));

        add(new JLabel("Score: " + score + "/" + total));
        add(new JLabel("Attempted: " + attempted));
        add(new JLabel("Correct: " + correct));
        add(new JLabel("Wrong: " + wrong));
        add(new JLabel("Unattempted: " + unattempted));

        JButton btnLogout = new JButton("Logout");
        add(btnLogout);

        btnLogout.addActionListener(e -> {
            new QuizLogin();
            dispose();
        });

        setVisible(true);
    }
}