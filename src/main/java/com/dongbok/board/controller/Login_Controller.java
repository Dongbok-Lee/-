package com.dongbok.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login_Controller {

    @GetMapping("/login")
    public String login_Form(){
        return "login";
    }
}
