package com.example.oppu.reply;

import com.example.oppu.board.Board;
import com.example.oppu.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    //댓글 생성하기
    public void create(Board board, String content, Member writer) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setWriter(writer);
        reply.setCreateDate(LocalDateTime.now());
        reply.setBoard(board);
        this.replyRepository.save(reply);
    }
}
