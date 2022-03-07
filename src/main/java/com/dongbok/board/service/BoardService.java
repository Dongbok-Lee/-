package com.dongbok.board.service;

import com.dongbok.board.model.Board;
import com.dongbok.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        int page = (pageable.getPageNumber()==0)?0:(pageable.getPageNumber()-1);
        pageable = PageRequest.of(page,10);
        return boardRepository.findAll(pageable);
    }

    public Board getBoard(int boardidx){
        return boardRepository.findById(boardidx).get();
    }

}
