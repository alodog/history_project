package com.zorin.history_testing.controller;

import com.zorin.history_testing.dao.UserInfoRep;
import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.Role;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Zorin Sergey
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserRep userRep;

    @Autowired
    private UserInfoRep userInfoRep;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user, @ModelAttribute("userInfo") UserInfo userInfo){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model,
                          @ModelAttribute("userInfo") @Valid UserInfo userInfo, BindingResult bindingResultInfo){
        if(bindingResult.hasErrors() || bindingResultInfo.hasErrors()){
            return "registration";
        }
        if(userRep.findByUsername(user.getUsername()) != null){
            model.addAttribute("message", "This Username exists!");
            return "registration";
        }
        user.setActive(true);
        user.setIdUserInfo(userInfo);
        userInfoRep.save(userInfo);
        userRep.save(user);
        return "redirect:/login";
    }

}
