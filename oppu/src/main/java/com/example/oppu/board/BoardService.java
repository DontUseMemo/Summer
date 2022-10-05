package com.example.oppu.board;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface BoardService {


    //게시글 리스트 불러오기
//    public List<Board> getList();

    public Board getBoardRequest(Long id);

    public void insertBoard(String category, String title, String nickname, String content);

//    public List<Board> searchEmail(String boardSearch);


 public Page<Board> getList(int page);
}