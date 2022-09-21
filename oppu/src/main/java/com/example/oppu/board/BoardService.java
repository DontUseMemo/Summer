package com.example.oppu.board;

import com.example.oppu.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public Optional<Board> getBoard(Board board) {
        return boardRepository.findById(board.getId());
    }

    public Board getBoardRequest(Long id) {
        Optional<Board> question = this.boardRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void insertBoard(String category, String title, String nickname, String content) {
        Board board = new Board();
        board.setCategory(category);
        board.setTitle(title);
        board.setNickname(nickname);
        board.setContent(content);
        board.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board);
    }

    public List<Board> searchEmail(String boardSearch) {
        return this.boardRepository.findBoardsByTitle(boardSearch);
    }

    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 2, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }
}
