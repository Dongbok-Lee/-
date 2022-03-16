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

    @Transactional
    public void delete(int boardId){
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void update(Board requestBoard){
        Board board = boardRepository.getById(requestBoard.getId());
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 함수가 끝나면 트랜잭션이 종료되면서 더티체킹하고 flush한다.
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
