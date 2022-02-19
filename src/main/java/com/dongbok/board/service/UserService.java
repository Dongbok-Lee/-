package com.dongbok.board.service;

import com.dongbok.board.model.User;
import com.dongbok.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void save(User user){
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
    }
}
