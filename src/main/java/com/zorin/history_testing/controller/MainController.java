package com.zorin.history_testing.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zorin Sergey
 */

@Controller
public class MainController {

    @GetMapping("/")
    public String starterPage(Model model){
        return "starter_page";
    }

    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("hello", "Hello Word! it is my main page!");
        return "main";
    }


}
