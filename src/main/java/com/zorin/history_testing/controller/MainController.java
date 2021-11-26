package com.zorin.history_testing.controller;

import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

/**
 * @author Zorin Sergey
 */

@Controller
public class MainController {

    @Autowired
    private UserRep userRep;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String starterPage(Model model){
        return "starter_page";
    }

    @GetMapping("/profile")
    public String mainPage(Model model, Principal principal){
        User user =  userService.getCurrentUser(principal);
        model.addAttribute("user", user);
        return "profile";
    }


}
