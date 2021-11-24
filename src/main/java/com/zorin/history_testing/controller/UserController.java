package com.zorin.history_testing.controller;

import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.Role;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zorin Sergey
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRep userRep;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRep.findAll());
        return "user_list";
    }

    @GetMapping("{id}")
    public String userEditForm(@PathVariable("id") int id, Model model){
//        User user = userRep.getById(id);
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Role.values());
        return "user_edit";
    }
}
