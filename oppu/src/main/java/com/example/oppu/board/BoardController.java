package com.example.oppu.board;

import com.example.oppu.member.Member;
import com.example.oppu.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    //게시판 목록
    @RequestMapping("/boardList")
    public String list(Model model,
                       @PageableDefault(size = 10, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(value = "searchCate", required = false, defaultValue = "")String searchCate,
                       @RequestParam(value = "searchKeyword", required = false, defaultValue = "")String searchKeyword) {

        model.addAttribute("searchCate", searchCate);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("paging", boardService.getList(searchCate, searchKeyword, pageable));

        return "/board/boardList";
    }

//    @RequestMapping("/boardList")
//    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
////        List<Board> boardList = this.boardService.getList();
////        model.addAttribute("boardList", boardList);
//        Page<Board> paging = this.boardService.getList(page);
//        model.addAttribute("paging", paging);
//        return "/board/boardList";
//    }

    //게시판 상세보기
    @RequestMapping(value = "/getBoard/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Board board = this.boardService.getBoardRequest(id);
        model.addAttribute("board", board);
        return "/board/getBoard";
    }


    //게시판 새글쓰기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insert")
    public String insertBoard() {
        return "/board/insertBoard";
    }

    //게시판 새글쓰기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/insert")
    public String insertBoard(@RequestParam String category,
                              @RequestParam String title,
                              @RequestParam String content,
                              Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        this.boardService.insertBoard(category, title, member, content);
        return "redirect:/board/boardList";
    }


}
