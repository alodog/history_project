package com.zorin.history_testing.service;

import com.zorin.history_testing.dao.UserInfoRep;
import com.zorin.history_testing.dao.UserRep;
import com.zorin.history_testing.entity.Role;
import com.zorin.history_testing.entity.User;
import com.zorin.history_testing.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zorin Sergey
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRep userRep;
    @Autowired
    private UserInfoRep userInfoRep;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRep.findByUsername(username);
    }
    public User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
    public void updateUser(int id, User user) {
        User userFromDB = userRep.getById(id);
        UserInfo userInfoFromDB = userFromDB.getUserInfo();
        if (userRep.findByUsername(user.getUsername())==null){
            userFromDB.setUsername(user.getUsername());
        }
        userFromDB.setRoles(user.getRoles());
        userInfoFromDB.setFirstName(user.getUserInfo().getFirstName());
        userInfoFromDB.setSurname(user.getUserInfo().getSurname());
        userInfoRep.save(userInfoFromDB);
        userRep.save(userFromDB);
    }

    public List<User> listUsersBySurname(String request){
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoRep.findAllBySurname(request).forEach(userInfoList::add);
        List<User> users = new ArrayList<>(userRep.findAll().stream().
                filter(user -> user.getUserInfo().getSurname().equals(request))
                .collect(Collectors.toList()));
        return users;
    }

    public UserService() {
    }


    public void updateProfile(int id, User user) {
        User userFromDB = userRep.getById(id);
        UserInfo userInfoFromDB = userFromDB.getUserInfo();
        if (userRep.findByUsername(user.getUsername())==null){
            userFromDB.setUsername(user.getUsername());
        }
        userInfoFromDB.setFirstName(user.getUserInfo().getFirstName());
        userInfoFromDB.setSurname(user.getUserInfo().getSurname());
        userInfoFromDB.setFirstName(user.getUserInfo().getFirstName());
        userInfoRep.save(userInfoFromDB);
        userRep.save(userFromDB);
    }
}
