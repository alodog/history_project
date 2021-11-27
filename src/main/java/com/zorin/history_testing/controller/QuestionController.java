package com.zorin.history_testing.controller;

import com.zorin.history_testing.dao.QuestionRep;
import com.zorin.history_testing.entity.Question;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.service.QuestionService;
import com.zorin.history_testing.service.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Zorin Sergey
 */

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRep questionRep;
    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public String getQuestions(Model model){
        model.addAttribute("questions", questionRep.findAll());
        return "questions";
    }

    @GetMapping("/add")
    public String addNewQuestion(@ModelAttribute("question") Question question){
        return "add_question";
    }

    @PostMapping()
    public String newQuestionAdded(@ModelAttribute("question") @Valid Question question,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add_question";
        }
        questionRep.save(question);
        return "redirect:/questions";
    }


    @GetMapping("/{id}")
    public String questionEditForm(@PathVariable("id")int id, Model model){
        Question question = questionRep.findById(id).get();
        model.addAttribute("question", question);
        return "question_edit";
    }

    @PostMapping ("/{id}")
    public String updateUser(@ModelAttribute ("question") @Valid Question question,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "question_edit";
        }

        questionService.updateQuestion(id, question);
        return "redirect:/questions";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id ){
        questionRep.deleteById(id);
        return "redirect:/questions";
    }


}
