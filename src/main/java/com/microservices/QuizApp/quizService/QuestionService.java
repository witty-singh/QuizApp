package com.microservices.QuizApp.quizService;

import com.microservices.QuizApp.Model.Question;
import com.microservices.QuizApp.quizRepo.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionRepository.getQuestionsByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Question> addQuestion(Question question){
        try{
            return new ResponseEntity<>(questionRepository.save(question),HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> deleteQuestion(Long id){
        try{
            Question ques=new Question();
            if(questionRepository.findById(id).isPresent()) {
                ques = questionRepository.getById(id);
                questionRepository.delete(ques);
            }
            return new ResponseEntity<>(ques, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Question(), HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Question> update(Question question) {
        try {
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }
}
