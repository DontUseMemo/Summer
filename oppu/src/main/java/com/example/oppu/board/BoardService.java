package com.example.oppu.board;

import com.example.oppu.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BoardService {


    //게시글 리스트 불러오기
    Board getBoardRequest(Long id);

    //게시글 등록하기
    void insertBoard(String category, String title, Member member, String content);

    //게시글 목록보기
    Page<Board> getList(int page);

    //게시글 카테고리, 검색하여 목록보기
    Page<Board> getList(String searchCate, String searchKeyword, Pageable pageable);

}