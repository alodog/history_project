package com.zorin.history_testing.controller;

import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.Role;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Zorin Sergey
 */
@Controller
@RequestMapping("/admins")
@PreAuthorize("hasAnyAuthority('ADMIN', 'MAINADMIN')")
public class AdminController {

    @Autowired
    private UserRep userRep;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String usersList(@RequestParam(value = "research", defaultValue = "") String research, Model model){
        if(research.isEmpty()){
            model.addAttribute("users", userRep.findAll());
            return "users_list";
        }
        List<User> usersBySurname = userService.listUsersBySurname(research);
        model.addAttribute("users", usersBySurname);
        model.addAttribute("research", research);
        return "users_list";
    }

    @GetMapping("/users/{id}")
    public String userEditForm(@PathVariable("id") int id, Model model){
        User user = userRep.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());
        return "user_edit";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id ){
        userRep.deleteById(id);
        return "redirect:/admins/users";
    }

    @PatchMapping ("/users/{id}")
    public String updateUser(@ModelAttribute ("user") @Valid User user,
                             BindingResult bindingResult,
                             @PathVariable("id") int id,
                             Model model ) {
        if(bindingResult.hasErrors()){
            return "user_edit";
        }
        if(!user.getUsername().equals(userRep.getById(id).getUsername())){
            if((userRep.findByUsername(user.getUsername()) != null)){
                model.addAttribute("message", "Это имя уже занято другим пользователем");
                return "user_edit";
            }
        }
        userService.updateUser(id, user);
        return "redirect:/admins/users";
    }
}

