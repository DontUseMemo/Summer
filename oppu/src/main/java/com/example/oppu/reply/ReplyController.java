package com.example.oppu.reply;

import com.example.oppu.board.Board;
import com.example.oppu.board.BoardService;
import com.example.oppu.member.Member;
import com.example.oppu.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final MemberService memberService;

    //댓글 생성하기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Long id, @RequestParam String reply, Principal principal) {
        Board board = this.boardService.getBoardRequest(id);
        Member member = this.memberService.getMember(principal.getName());
        this.replyService.create(board, reply, member);
        return String.format("redirect:/board/getBoard/%s", id);
    }
}
