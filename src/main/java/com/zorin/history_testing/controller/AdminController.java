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

/**
 * @author Zorin Sergey
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN', 'MAINADMIN')")
public class AdminController {

    @Autowired
    private UserRep userRep;
    @Autowired
    private UserService userService;

    @GetMapping
    public String mainAdminPage(){
        return "admin";
    }

    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("users", userRep.findAll());
        return "user_list";
    }

    @GetMapping("/users/{id}")
    public String userEditForm(@PathVariable("id") int id, Model model){
        User user = userRep.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());
        return "user_edit";
    }

    @PostMapping ("/users/{id}")
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
        return "redirect:/admin/users";
    }
}











//    @PostMapping("/users")
//    public String updateUser(@RequestParam("userName") @Valid String userName,
//                             @RequestParam ("userId") int id,
//                             @RequestParam(value = "roles", required = false) Set< String> form) {
////        if(bindingResult.hasErrors()){
////            return "user_edit";
////        }
//        User user = userRep.getById(id);
//
//        if (form!=null){
//             userService.updateUser(user, userName, form);
//        } else {
//             form = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
//             userService.updateUser(user, userName, form);
//        }
//        return "redirect:/admin/users";
//    }