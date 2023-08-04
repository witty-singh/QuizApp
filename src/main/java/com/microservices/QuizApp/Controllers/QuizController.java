package com.microservices.QuizApp.Controllers;

import com.microservices.QuizApp.Model.Question;
import com.microservices.QuizApp.Model.QuestionWrapper;
import com.microservices.QuizApp.Model.Quiz;
import com.microservices.QuizApp.Model.Response;
import com.microservices.QuizApp.quizService.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int numQ, @RequestParam String category){
        return quizService.createQuiz(title, numQ,category);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Long quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> responses){
        return quizService.calculateResult(quizId, responses);
    }
}
