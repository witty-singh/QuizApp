package com.microservices.QuizApp.quizService;

import com.microservices.QuizApp.Model.Question;
import com.microservices.QuizApp.Model.QuestionWrapper;
import com.microservices.QuizApp.Model.Quiz;
import com.microservices.QuizApp.Model.Response;
import com.microservices.QuizApp.quizRepo.QuestionDAO;
import com.microservices.QuizApp.quizRepo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String title, int numQ, String category){


        List<Question> questionList = questionDAO.getRandomQuestionsByCategory(numQ, category);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);

        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz created" , HttpStatus.OK);
    }

    //Quiz for client side
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        for(Question question : questionsFromDB){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(),
                    question.getOption3(), question.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Long quizId, List<Response> responses) {
        Optional<Quiz> quiz= quizDao.findById(quizId);
        List<Question> questionList= quiz.get().getQuestions();
        int correctAnswers=0;
        int i=0;
        for(Response response : responses){
            if(response.getAnswer().equals(questionList.get(i).getRightAnswer())){
                correctAnswers++;
            }
            i++;
        }
        return new ResponseEntity<>(correctAnswers,HttpStatus.OK);
    }
}
