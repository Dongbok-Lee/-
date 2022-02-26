package com.dongbok.board.controller;

import com.dongbok.board.config.auth.PrincipalDetail;
import com.dongbok.board.config.auth.PrincipalDetailService;
import com.dongbok.board.model.User;
import com.dongbok.board.repository.UserRepository;
import com.dongbok.board.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Signup_ControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PrincipalDetailService principalDetailService;

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

   @Test
    public void Pwd_check(){
        String pwd = bCryptPasswordEncoder.encode("test");
       System.out.println(bCryptPasswordEncoder.matches("test", pwd));;
        Optional<User> user = userRepository.findByEmail("aa@naver.com");
       if(user.isPresent()) System.out.println(bCryptPasswordEncoder.matches("test",user.get().getPassword()));
       else System.out.println("없습니다"); ;
   }

   @Test
    public void security_test(){
       System.out.println(principalDetailService.loadUserByUsername("aa@naver.com"));
   }
}
