package com.zorin.history_testing.service;

import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.Role;
import com.zorin.history_testing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zorin Sergey
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRep.findByUsername(username);
    }

    public User getCurrentUser(Principal principal) {

        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

    public void updateUser(int id, User user) {
        User userFromDB = userRep.getById(id);
        if (userRep.findByUsername(user.getUsername())==null){
            userFromDB.setUsername(user.getUsername());
        }
        userFromDB.setRoles(user.getRoles());
        userRep.save(userFromDB);
    }

    public UserService() {
    }


}


//    public void updateUser(User user, String newName, Set<String> form){
//
//        if (userRep.findByUsername(newName)==null){
//            user.setUsername(newName);
//        }
//
//        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
//        user.getRoles().clear();
//
//        for (String formRole : form) {
//            if(roles.contains(formRole)){
//                user.getRoles().add(Role.valueOf(formRole));
//            }
//        }
//
//        if(!(user.getRoles().contains(Role.USER))){
//            user.getRoles().add(Role.USER);
//        }
//
//        userRep.save(user);
//    }