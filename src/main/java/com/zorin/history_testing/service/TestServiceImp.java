package com.zorin.history_testing.service;

import com.zorin.history_testing.any_classes.FullQuestion;
import com.zorin.history_testing.any_classes.FullUsersTestResult;
import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zorin Sergey
 */
@Data
@Service
public class TestServiceImp implements TestService{

    private FullUsersTestResult testResult;
    private List<FullQuestion> fullQuestions;
    @Autowired
    UserRep userRep;


    @Override
    public void save(FullUsersTestResult testResult, User user) {
        this.testResult = testResult;
        List<String> userAnswers = testResult.getAnswers();
        List<String> answers = fullQuestions.stream().map(FullQuestion::getTrueAnswer).collect(Collectors.toList());
        int count=0;
        for (int i = 0; i < userAnswers.size(); i++) {
            if(userAnswers.get(i).equals(answers.get(i))){
                count++;
            }
        }
        String rating = String.format("Количество верных ответов: %d из %d!",count,answers.size());
        user.setResult(rating);
        userRep.save(user);
    }

    public TestServiceImp() {
    }
}
