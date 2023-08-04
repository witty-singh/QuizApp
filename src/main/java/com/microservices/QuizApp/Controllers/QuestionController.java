package com.microservices.QuizApp.Controllers;

import com.microservices.QuizApp.Model.Question;
import com.microservices.QuizApp.quizService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @DeleteMapping("deleteQuestion/{quesId}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long quesId){
        return questionService.deleteQuestion(quesId);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("updateQuestion")
    public ResponseEntity<Question> updateQuestion( @RequestBody Question question){
        return questionService.update(question);
    }
}
