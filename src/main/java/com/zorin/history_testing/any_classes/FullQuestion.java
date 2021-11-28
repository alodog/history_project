package com.zorin.history_testing.any_classes;

import com.zorin.history_testing.entity.Question;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zorin Sergey
 */
@Data
@Component
public class FullQuestion {


    private String questionText;
    private List<String> answers;
    private String trueAnswer;

    public FullQuestion() {
    }

    public FullQuestion(Question question) {
        questionText = question.getQuestionText();
        trueAnswer = question.getTrueAnswer();
        answers = new ArrayList<>();
        answers.add(trueAnswer);
        answers.add(question.getWrongAnswer0());
        answers.add(question.getWrongAnswer1());
        answers.add(question.getWrongAnswer2());
        Collections.shuffle(answers);
        }

}

