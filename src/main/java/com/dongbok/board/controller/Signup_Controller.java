package com.dongbok.board.controller;

import com.dongbok.board.model.User;
import com.dongbok.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Signup_Controller {

    private final UserService userService;

    @Autowired
    public Signup_Controller(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup_form(){
        return "signup";
    }

    @PostMapping("/user")
    public String signup(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/login";
    }
}
