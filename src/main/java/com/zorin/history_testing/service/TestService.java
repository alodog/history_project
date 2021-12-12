/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.service;

import com.zorin.history_testing.any_classes.FullQuestion;
import com.zorin.history_testing.any_classes.FullUsersTestResult;
import com.zorin.history_testing.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public interface TestService {

    void save(FullUsersTestResult testResult, User user);
    FullUsersTestResult getTestResult();
    List<FullQuestion> getFullQuestions();
    void setFullQuestions(List<FullQuestion> fullQuestions);

}
