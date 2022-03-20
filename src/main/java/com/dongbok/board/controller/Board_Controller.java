package com.dongbok.board.controller;

import com.dongbok.board.model.Board;
import com.dongbok.board.model.Reply;
import com.dongbok.board.model.User;
import com.dongbok.board.repository.BoardRepository;
import com.dongbok.board.repository.ReplyRepository;
import com.dongbok.board.repository.UserRepository;
import com.dongbok.board.service.BoardService;
import com.dongbok.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final ReplyService replyService;

    @Autowired
    public Board_Controller(ReplyService replyService,BoardService boardService, UserRepository userRepository, ReplyRepository replyRepository){
        this.boardService = boardService;
        this.userRepository = userRepository;
        this.replyRepository = replyRepository;
        this.replyService = replyService;
    }

    @GetMapping("/")
    public String index(Principal principal,Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("list",boardService.getBoardList(pageable));
        if(principal != null){
            User user = userRepository.findByEmail(principal.getName()).get();
            model.addAttribute("user",user);
        }

        return "index";
    }

    @GetMapping("/write")
    public String writeForm(Principal principal, Model model){
        if(principal != null){
            User user = userRepository.findByEmail(principal.getName()).get();
            model.addAttribute("user",user);
        }
        return "write";
    }

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
    public String viewDetail(Principal principal,Model model, @RequestParam(value = "boardidx") int boardIdx){
        Board board = boardService.getBoard(boardIdx);
        //조회수 증가
        int cnt = board.getCount()+1;
        board.setCount(cnt);
        boardService.save(board);

        model.addAttribute("board", board);
        if(principal != null){
            User user = userRepository.findByEmail(principal.getName()).get();
            model.addAttribute("user",user);
        }
        return "board";
    }

    @GetMapping("/edit")
    public String editForm(Principal principal, Model model, @RequestParam(value = "boardidx") int boardIdx){
        Board board = boardService.getBoard(boardIdx);
        model.addAttribute("board", board);
        if(principal != null){
            User user = userRepository.findByEmail(principal.getName()).get();
            model.addAttribute("user",user);
        }
        return "edit";
    }

    @PostMapping("/edit_post")
    public String editPost(@ModelAttribute Board board){
        boardService.update(board);
        return "redirect:";
    }
    @PostMapping("/delete_post")
    public String deletePost(@RequestParam(value = "boardidx") int boardidx){
        boardService.delete(boardidx);
        return "redirect:";
    }

    @PostMapping("/delete_reply")
    public String deleteReply(@RequestParam(value = "replyidx")int replyidx){
        replyService.delete(replyidx);
        return":redirect";
    }
}
