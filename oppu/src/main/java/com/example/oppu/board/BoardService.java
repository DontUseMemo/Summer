package com.example.oppu.board;

import com.example.oppu.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {


    //게시글 리스트 불러오기
    Board getBoardRequest(Long id);

    //게시글 등록하기
    Long insertBoard(String title, String category, String content, Member member);

    //게시글 목록보기
    Page<Board> getList(int page);

    //게시글 카테고리, 검색하여 목록보기
    Page<Board> getList(String searchCate, String searchKeyword, Pageable pageable);

    // 게시글 수정하기
    void modify(Board board, String title, String category, String content);

    Long insertFileUploadEntity(FileUploadEntity fileUploadEntity);

    List<FileUploadEntity> getFileuploadEntity2(Long board_seq);

    Board deleteBoard(Long id);
}