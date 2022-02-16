package com.dongbok.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Signup_Controller {
    @GetMapping("/signup")
    public String signup_form(){
        return "signup";
    }
}
