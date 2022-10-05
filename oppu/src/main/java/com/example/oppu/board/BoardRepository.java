package com.example.oppu.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    //제목으로 찾기(이후 검색에 추가될 예정)
//    Board findByTitle(String title);

//    //제목과 내용으로 찾기(이후 검색에 추가될지 미정)
//    Board findByTitleAndContent(String title, String content);

//    //
//    @Query(value = "select b from Board b where b.title like concat('%', :boardSearch, '%') ")
//    List<Board> findBoardsByTitle(String boardSearch);

    //기본 페이징
    Page<Board> findAll(Pageable pageable);

}
