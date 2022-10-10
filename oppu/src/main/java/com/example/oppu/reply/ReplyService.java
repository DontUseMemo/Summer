package com.example.oppu.reply;

import com.example.oppu.board.Board;
import com.example.oppu.member.Member;
import org.springframework.stereotype.Service;


public interface ReplyService {

    //댓글 생성하기
    public void create(Board board, String content, Member writer);
}
