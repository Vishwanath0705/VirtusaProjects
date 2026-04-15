package StudentPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class QuizPage extends JFrame {

    JLabel lblQuestion, lblTimer;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;
    JButton btnNext;

    int currentId = 1;
    int timeLeft = 45;
    Timer timer;

    int score = 0;
    int correct = 0;
    int wrong = 0;
    int attempted = 0;
    int totalQuestions = 0;

    String correctAnswer = "";

    String name, roll, email, phone;

    public QuizPage(String name, String roll, String email, String phone) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.phone = phone;

        setTitle("Quiz");
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        lblTimer = new JLabel("Time: 45");
        lblTimer.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(lblTimer, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        lblQuestion = new JLabel("Loading...");
        center.add(lblQuestion);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        center.add(opt1);
        center.add(opt2);
        center.add(opt3);
        center.add(opt4);

        btnNext = new JButton("Next");
        center.add(btnNext);

        add(center, BorderLayout.CENTER);

        btnNext.addActionListener(e -> nextQuestion());

        loadTotalQuestions();
        loadQuestion();
        startTimer();

        setVisible(true);
    }

    private void loadTotalQuestions() {
        try {
            Connection con = Database.DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM question");
            if (rs.next()) totalQuestions = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadQuestion() {
        try {
            Connection con = Database.DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM question WHERE id=?");
            ps.setInt(1, currentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lblQuestion.setText(rs.getString("question"));
                opt1.setText(rs.getString("option1"));
                opt2.setText(rs.getString("option2"));
                opt3.setText(rs.getString("option3"));
                opt4.setText(rs.getString("option4"));
                correctAnswer = rs.getString("answer");
                group.clearSelection();
                timeLeft = 45;

                if (currentId == totalQuestions) btnNext.setText("Submit");
                else btnNext.setText("Next");
            } else {
                finishQuiz();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void evaluate() {
        String selected = null;

        if (opt1.isSelected()) selected = opt1.getText();
        if (opt2.isSelected()) selected = opt2.getText();
        if (opt3.isSelected()) selected = opt3.getText();
        if (opt4.isSelected()) selected = opt4.getText();

        if (selected != null) {
            attempted++;
            if (selected.equals(correctAnswer)) {
                score++;
                correct++;
            } else {
                score--;
                wrong++;
            }
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeLeft--;
                lblTimer.setText("Time: " + timeLeft);
                if (timeLeft <= 0) nextQuestion();
            }
        }, 0, 1000);
    }

    private void nextQuestion() {
        evaluate();

        if (btnNext.getText().equals("Submit")) {
            finishQuiz();
            return;
        }

        currentId++;
        loadQuestion();
    }

    private void finishQuiz() {
        timer.cancel();

        int unattempted = totalQuestions - attempted;
        double percentage = (score * 100.0) / totalQuestions;

        saveResult(score, percentage);

        new ResultPage(score, totalQuestions, attempted, correct, wrong, unattempted);
        dispose();
    }

    private void saveResult(int score, double percentage) {
        try {
            Connection con = Database.DbConnection.getConnection();

            String query = "INSERT INTO student(name, roll_no, email, phone_number, marks, total_questions, percentage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, score);
            ps.setInt(6, totalQuestions);
            ps.setDouble(7, percentage);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}