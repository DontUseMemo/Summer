package com.example.oppu.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    //게시판 목록
    @RequestMapping("/boardList")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
//        List<Board> boardList = this.boardService.getList();
//        model.addAttribute("boardList", boardList);
        Page<Board> paging = this.boardService.getList(page);
        model.addAttribute("paging", paging);
        return "/board/boardList";
    }

    //게시판 상세보기
    @RequestMapping(value = "/getBoard/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Board board = this.boardService.getBoardRequest(id);
        model.addAttribute("board", board);
        return "/board/getBoard";
    }


    //게시판 새글쓰기
    @GetMapping("/insert")
    public String insertBoard() {
        return "/board/insertBoard";
    }

    //게시판 새글쓰기
    @PostMapping("/insert")
    public String insertBoard(@RequestParam String category,
                              @RequestParam String title,
                              @RequestParam String nickname,
                              @RequestParam String content) {
        this.boardService.insertBoard(category, title, nickname, content);
        return "redirect:/board/boardList";
    }

//    @PostMapping("/search")
//    public String searchBoard(@RequestParam("boardSearch") String boardSearch) {
//        this.boardService.searchEmail(boardSearch);
//        return "redirect:/board/list";
//    }
}
