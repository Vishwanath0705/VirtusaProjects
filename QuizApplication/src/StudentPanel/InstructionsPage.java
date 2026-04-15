package StudentPanel;

import javax.swing.*;
import java.awt.*;

public class InstructionsPage extends JFrame {

    String name, roll, email, phone;

    public InstructionsPage(String name, String roll, String email, String phone) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.phone = phone;

        setTitle("Instructions");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Quiz Instructions");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ✅ Show student info
        JLabel info = new JLabel("Name: " + name + " | Roll: " + roll);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea instructions = new JTextArea(
                "1. Each question has 45 seconds.\n" +
                "2. No going back.\n" +
                "3. Correct +1, Wrong -1.\n"
        );
        instructions.setEditable(false);

        JButton btnStart = new JButton("Start Quiz");
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(info);
        panel.add(Box.createVerticalStrut(20));
        panel.add(instructions);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnStart);

        add(panel);

        setVisible(true);

        // ✅ Move to Quiz Page with data
        btnStart.addActionListener(e -> {
            new QuizPage(name, roll, email, phone);
            dispose();
        });
    }
}