package com.example.oppu.reply;

import com.example.oppu.board.Board;
import org.springframework.stereotype.Service;


@Service
public interface ReplyService {

    public void create(Board board, String content);
}
