package com.example.oppu.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //기본 페이징
    @Query(value = "select b from Board b where b.deleteYN = 'N'")
    Page<Board> findAll(Pageable pageable);

    //제목 검색 페이징
    Page<Board> findByTitleContaining(String title, Pageable pageable);

    //작성자 검색 페이징
    Page<Board> findByWriterContaining(String writer, Pageable pageable);

    //내용 검색 페이징
    Page<Board> findByContentContaining(String content, Pageable pageable);
}
