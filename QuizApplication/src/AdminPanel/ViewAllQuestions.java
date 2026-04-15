package AdminPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewAllQuestions extends JFrame {

    JTextArea textArea;

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

        // ✅ Back Button Panel (IMPORTANT FIX)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 40)); // ✅ size control

        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH); // ✅ correct placement

        loadData();

        btnBack.addActionListener(e -> {
            new AdminMenu();
            dispose();
        });

        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = Database.DbConnection.getConnection();
            String query = "SELECT * FROM question";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("Question ID: ").append(rs.getString("id")).append("\n");
                sb.append("Question: ").append(rs.getString("question")).append("\n");
                sb.append("Option 1: ").append(rs.getString("option1")).append("\n");
                sb.append("Option 2: ").append(rs.getString("option2")).append("\n");
                sb.append("Option 3: ").append(rs.getString("option3")).append("\n");
                sb.append("Option 4: ").append(rs.getString("option4")).append("\n");
                sb.append("Answer: ").append(rs.getString("answer")).append("\n");

                int lineLength = 80; // ✅ fixed separator (better than getWidth)
                sb.append("-".repeat(lineLength)).append("\n\n");
            }

            textArea.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewAllQuestions();
    }
}