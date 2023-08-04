package com.microservices.QuizApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {
    private long id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
