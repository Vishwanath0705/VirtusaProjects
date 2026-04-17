package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import models.Question;
import service.QuestionService;
import service.StudentService;

public class QuizPage extends JFrame {

    JLabel lblQuestion, lblTimer;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;
    JButton btnNext;

    int currentIndex = 0;
    int timeLeft = 45;

    int score = 0;
    int correct = 0;
    int wrong = 0;
    int attempted = 0;

    int totalQuestions = 0;

    String correctAnswer = "";

    String name, roll, email, phone;

    QuestionService questionService = new QuestionService();
    StudentService studentService = new StudentService();

    List<Question> questions;

    javax.swing.Timer swingTimer;

    // 🔥 FIX: prevents double scoring
    boolean answered = false;

    public QuizPage(String name, String roll, String email, String phone) {

        this.name = name;
        this.roll = roll;
        this.email = email;
        this.phone = phone;
        
        if (studentService.hasWrittenExam(roll)) {
            JOptionPane.showMessageDialog(this, "You have already written the exam!");

            dispose();
            return;
        }

        setTitle("Quiz");
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Timer panel
        JPanel topPanel = new JPanel(new BorderLayout());
        lblTimer = new JLabel("Time: 45");
        lblTimer.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(lblTimer, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Center UI
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

        // Load questions once
        questions = questionService.getAllQuestions();
        totalQuestions = questions.size();

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions found!");
            dispose();
            return;
        }

        loadQuestion();
        startTimer();

        setVisible(true);
    }

    private void loadQuestion() {

        if (currentIndex < questions.size()) {

            Question q = questions.get(currentIndex);

            lblQuestion.setText(q.getQuestion());
            opt1.setText(q.getOption1());
            opt2.setText(q.getOption2());
            opt3.setText(q.getOption3());
            opt4.setText(q.getOption4());

            correctAnswer = q.getAnswer();

            group.clearSelection();

            timeLeft = 45;
            lblTimer.setText("Time: 45");

            answered = false; // 🔥 RESET for new question

            if (currentIndex == questions.size() - 1) {
                btnNext.setText("Submit");
            } else {
                btnNext.setText("Next");
            }

        } else {
            finishQuiz();
        }
    }

    private void evaluate() {

        if (answered) return; // 🔥 prevent double scoring

        String selected = null;

        if (opt1.isSelected()) selected = opt1.getText();
        else if (opt2.isSelected()) selected = opt2.getText();
        else if (opt3.isSelected()) selected = opt3.getText();
        else if (opt4.isSelected()) selected = opt4.getText();

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

        answered = true;
    }

    private void startTimer() {

        swingTimer = new javax.swing.Timer(1000, e -> {

            timeLeft--;
            lblTimer.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                swingTimer.stop(); // 🔥 prevent overlap
                nextQuestion();
            }
        });

        swingTimer.start();
    }

    private void nextQuestion() {

        evaluate();

        swingTimer.stop(); 

        currentIndex++;

        if (currentIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        loadQuestion();
        startTimer(); 
    }

    private void finishQuiz() {

        swingTimer.stop();

        int unattempted = totalQuestions - attempted;

        double percentage = totalQuestions == 0
                ? 0
                : (score * 100.0) / totalQuestions;

        models.Student s = new models.Student();
        s.setName(name);
        s.setRollNo(roll);
        s.setEmail(email);
        s.setPhoneNumber(phone);
        s.setMarks(score);
        s.setTotalQuestions(totalQuestions);
        s.setPercentage(percentage);

        try {
            studentService.saveResult(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ResultPage(score, totalQuestions, attempted, correct, wrong, unattempted);
        dispose();
    }
}