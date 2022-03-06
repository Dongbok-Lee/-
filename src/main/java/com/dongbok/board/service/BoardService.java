package com.dongbok.board.service;

import com.dongbok.board.model.Board;
import com.dongbok.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void save(Board board){
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
}
