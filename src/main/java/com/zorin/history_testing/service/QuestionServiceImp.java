package com.zorin.history_testing.service;

import com.zorin.history_testing.any_classes.FullQuestion;
import com.zorin.history_testing.any_classes.FullUsersTestResult;
import com.zorin.history_testing.dao.QuestionRep;
import com.zorin.history_testing.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zorin Sergey
 */
@Service
public class QuestionServiceImp implements QuestionService{

    @Autowired
    QuestionRep questionRep;
    @Autowired
    TestService testService;

    public QuestionServiceImp() {
    }

    @Override
    public void updateQuestion(int id, Question question) {
        Question questionFromDB = questionRep.findById(id).get();
        questionFromDB.setQuestionText(question.getQuestionText());
        questionFromDB.setTrueAnswer(question.getTrueAnswer());
        questionFromDB.setWrongAnswer0(question.getWrongAnswer0());
        questionFromDB.setWrongAnswer1(question.getWrongAnswer1());
        questionFromDB.setWrongAnswer2(question.getWrongAnswer2());
        questionRep.save(questionFromDB);
    }

    @Override
    public List<FullQuestion> getRandomQuestions() {
        List<Question> allQuestions =new ArrayList<>();
                questionRep.findAll().forEach(allQuestions::add);
        Collections.shuffle(allQuestions);
        List<FullQuestion> randomQuestions = allQuestions.stream().limit(5).map(FullQuestion::new).collect(Collectors.toList());
        testService.setFullQuestions(randomQuestions);
        return randomQuestions;
    }




}
