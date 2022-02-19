package com.dongbok.board.controller;

import com.dongbok.board.model.User;
import com.dongbok.board.repository.UserRepository;
import com.dongbok.board.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Signup_ControllerTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    @Rollback(false)
    public void joinMember(){
       User user = new User();
       user.setEmail("aa@naver.com");
       user.setNickname("test");
       user.setPassword("test");

       userService.save(user);
   }
}