/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.service;

import com.zorin.history_testing.any_classes.FullQuestion;
import com.zorin.history_testing.entity.Question;

import java.util.List;

public interface QuestionService {

    void updateQuestion(int id, Question question);
    List<FullQuestion> getRandomQuestions();
}
