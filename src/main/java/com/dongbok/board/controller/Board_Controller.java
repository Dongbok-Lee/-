package com.dongbok.board.controller;

import com.dongbok.board.model.Board;
import com.dongbok.board.model.Reply;
import com.dongbok.board.model.User;
import com.dongbok.board.repository.BoardRepository;
import com.dongbok.board.repository.ReplyRepository;
import com.dongbok.board.repository.UserRepository;
import com.dongbok.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class Board_Controller {

    private final BoardService boardService;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public Board_Controller(BoardService boardService, UserRepository userRepository, ReplyRepository replyRepository){
        this.boardService = boardService;
        this.userRepository = userRepository;
        this.replyRepository = replyRepository;
    }

    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("list",boardService.getBoardList(pageable));
        return "index";
    }

    @GetMapping("/write")
    public String writeForm(){ return "write";}

    @PostMapping("/write_post")
    public String writePost(@ModelAttribute Board board, Principal principal){
        String email = principal.getName();
        System.out.println(email);
        User user = userRepository.findByEmail(email).get();
        System.out.println(user);
        board.setUser(user);
        boardService.save(board);
        return "redirect:/";
    }

    @PostMapping("/write_comment")
    public String writeComment(@ModelAttribute Reply reply, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        reply.setUser(user);
        replyRepository.save(reply);
        return "redirect:";
    }

    @GetMapping("/board")
    public String viewDetail(Model model, @RequestParam(value = "boardidx") int boardIdx){
        Board board = boardService.getBoard(boardIdx);
        model.addAttribute("board", board);
        return "board";
    }
}
