package service;

import dao.QuestionDao;
import java.sql.SQLException;
import models.Question;

import java.util.List;

public class QuestionService {

    private QuestionDao dao = new QuestionDao();
    
    //  Add Question
    public boolean addQuestion(Question q) throws SQLException {

        if (q.getId() <= 0) {
            throw new RuntimeException("Invalid Question ID");
        }

        if (dao.isIdExists(q.getId())) {
            throw new RuntimeException("Question ID already exists");
        }

        return dao.addQuestion(q);
    }

    // Update Question
    public boolean updateQuestion(Question q) {
        if (q.getId() <= 0) {
            throw new RuntimeException("Invalid ID");
        }
        return dao.updateQuestion(q);
    }

    // Delete Question
    public boolean deleteQuestion(int id) {
        if (id <= 0) {
            throw new RuntimeException("Invalid ID");
        }
        return dao.deleteQuestion(id);
    }

    //  Get All Questions
    public List<Question> getAllQuestions() {
        return dao.getAllQuestions();
    }

    //  Helper
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public int getTotalQuestions() {
        try {
            return dao.getQuestionCount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }   
    
    public Question getQuestionById(int id) {
        return dao.getQuestionById(id);
    }
    
}
