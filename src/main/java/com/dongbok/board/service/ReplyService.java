package com.dongbok.board.service;

import com.dongbok.board.model.Reply;
import com.dongbok.board.repository.ReplyRepository;
import com.dongbok.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository){
        this.replyRepository = replyRepository;
    }

    @Transactional
    public void save(Reply reply){
        replyRepository.save(reply);
    }

    @Transactional
    public void delete(int replyid){
        replyRepository.deleteById(replyid);
    }

}
