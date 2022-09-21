package com.example.oppu.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByTitle(String title);

    Board findByTitleAndContent(String title, String content);

    List<Board> findByTitleLike(String title);

    @Query(value = "select b from Board b where b.title like concat('%', :boardSearch, '%') ")
    List<Board> findBoardsByTitle(String boardSearch);

    Page<Board> findAll(Pageable pageable);

}
