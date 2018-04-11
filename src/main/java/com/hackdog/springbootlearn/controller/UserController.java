package com.hackdog.springbootlearn.controller;

import com.hackdog.springbootlearn.pojo.User;
import com.hackdog.springbootlearn.service.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;


    @GetMapping("/list")
    public List<User> list() {
        return userJPA.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public List<User> delete(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        userJPA.delete(user);
        return userJPA.findAll();
    }

    @RequestMapping(value = "/get/{id}")
    public User get(@PathVariable("id") Long id) {
        return userJPA.getOne(id);
    }

    @PostMapping(value = "/add")
    public User add(User user) {
        return userJPA.save(user);
    }
}
