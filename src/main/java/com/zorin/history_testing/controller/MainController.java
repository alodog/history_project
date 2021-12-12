package com.zorin.history_testing.controller;

import com.zorin.history_testing.any_classes.FullUsersTestResult;
import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.service.QuestionService;
import com.zorin.history_testing.service.TestService;
import com.zorin.history_testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Zorin Sergey
 */

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    TestService testService;
    @Autowired
    private UserRep userRep;

    @GetMapping("/")
    public String starterPage(){
        return "starter_page";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        User currentUser =  userService.getCurrentUser();
        User currentUserFromDB = userRep.getById(currentUser.getId());
        if (currentUserFromDB.getResult()==null){
            currentUserFromDB.setResult("Вы еще не проходили тест!");
        }
        model.addAttribute("user", currentUserFromDB);
        return "profile";
    }

    @GetMapping("/test")
    public String startTest(Model model){
        model.addAttribute("result", new FullUsersTestResult());
        model.addAttribute("questions", questionService.getRandomQuestions());
        return "test";
    }

    @PostMapping("/test")
    public String testEnded(@ModelAttribute("result") FullUsersTestResult  fullUsersTestResult, Principal principal){
        User user =  userService.getCurrentUser();
        testService.save(fullUsersTestResult, user);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String getResultPage(Model model, Principal principal){
        User user =  userService.getCurrentUser();
        if (user.getResult()==null){
            user.setResult("Вы еще не проходили тест!");
        }
        model.addAttribute("user", user);
        model.addAttribute("questions", testService.getFullQuestions());
        model.addAttribute("result", testService.getTestResult());
        return "result_page";
    }

    @PatchMapping("/profile")
    public String updateProfile(@ModelAttribute ("user") @Valid User user,
                             BindingResult bindingResult, Principal principal, Model model) {
        if(bindingResult.hasErrors()){
            return "profile";
        }
        int id = userService.getCurrentUser().getId();
        if(!user.getUsername().equals(userRep.getById(id).getUsername())){
            if((userRep.findByUsername(user.getUsername()) != null)){
                model.addAttribute("message", "Это имя уже занято другим пользователем");
                return "profile";
            }
        }
        userService.updateProfile(id, user);
        return "redirect:/";
    }
}
