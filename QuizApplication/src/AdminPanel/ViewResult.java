package AdminPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class ViewResult extends JFrame {
    JTextArea textArea;

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

        // ✅ Back Button Panel (FIX)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(120, 40)); // ✅ size

        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH); // ✅ correct placement

        btnBack.addActionListener(e -> {
            new AdminMenu();
            dispose();
        });

        loadData();

        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = Database.DbConnection.getConnection();
            String query = "SELECT * FROM student";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("Student ID: ").append(rs.getString("id")).append("\n");
                sb.append("Student Name: ").append(rs.getString("name")).append("\n");
                sb.append("Roll Number: ").append(rs.getString("roll_no")).append("\n");
                sb.append("Email: ").append(rs.getString("email")).append("\n");
                sb.append("Phone Number: ").append(rs.getString("phone_number")).append("\n");
                sb.append("Marks: ").append(rs.getString("marks")).append("\n");
                sb.append("Total Questions: ").append(rs.getString("total_questions")).append("\n");
                sb.append("Percentage: ").append(rs.getString("percentage")).append("\n");

                int lineLength = 80; // ✅ fixed separator
                sb.append("-".repeat(lineLength)).append("\n\n");
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