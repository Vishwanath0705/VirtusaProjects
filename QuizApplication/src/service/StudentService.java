package service;

import dao.StudentDao;
import java.sql.SQLException;
import models.Student;

import java.util.List;

public class StudentService {

    private StudentDao dao = new StudentDao();

    // 🔥 Save Result
    public boolean saveResult(Student s) throws SQLException {

        if (s == null || isEmpty(s.getName())) {
            throw new RuntimeException("Invalid student data");
        }

        double percentage = (s.getMarks() * 100.0) / s.getTotalQuestions();
        s.setPercentage(percentage);

        return dao.saveStudentResults(s);
    }

    // 🔥 View Results
    public List<Student> getAllResults() throws SQLException {
        return dao.getAllResult();
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public boolean hasWrittenExam(String rollNo) {
        return dao.isExamAlreadyTaken(rollNo);
    }
    
}