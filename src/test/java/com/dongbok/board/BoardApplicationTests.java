package com.dongbok.board;

import com.dongbok.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

@SpringBootTest
class BoardApplicationTests {

    @Autowired
    private BoardService boardService;

    @Test
    void contextLoads(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println(boardService.getBoardList(pageable));
    }

}
