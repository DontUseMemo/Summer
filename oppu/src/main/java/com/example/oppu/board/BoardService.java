package com.example.oppu.board;

import com.example.oppu.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BoardService {


    //게시글 리스트 불러오기
//    public List<Board> getList();

    Board getBoardRequest(Long id);

    void insertBoard(String category, String title, Member member, String content);

//    public List<Board> searchEmail(String boardSearch);

    Page<Board> getList(int page);
}