package com.dongbok.board.config.auth;

import com.dongbok.board.model.User;
import com.dongbok.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PrincipalDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByEmail(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
                });

        return new PrincipalDetail(principal); //시큐리티의 세션에 유저정보가 저장이 됨.
    }
}